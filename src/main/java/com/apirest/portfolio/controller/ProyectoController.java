/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.cloudinary.service.CloudinaryService;
import com.apirest.portfolio.dto.Imagen;
import com.apirest.portfolio.dto.Mensaje;
import com.apirest.portfolio.model.Proyecto;
import com.apirest.portfolio.service.IProyectoService;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PCcito
 */
@RestController
//@CrossOrigin(origins="http://localhost:4200")
@CrossOrigin(origins="https://app-portfolio-36e26.web.app")
public class ProyectoController {
    @Autowired 
    private IProyectoService interProyecto;
    
    @Autowired
    private CloudinaryService cloudinaryService;
    
    @Value("${image.default.proyecto.nombre}")
    private String imagen;
    
    @Value("${image.default.proyecto.public.id}")
    private String imagen_public_id;
    
    @Value("${image.default.proyecto.url}")
    private String imagen_url;
    
    @GetMapping("proyecto/traer")
    public ResponseEntity<List<Proyecto>> getProyectos(){
        try {
            List<Proyecto> pro = interProyecto.getProyectos();
            if (pro.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(pro, HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("proyecto/crear")
    public ResponseEntity<Proyecto> createProyecto(@RequestBody Proyecto pro){
        try {
            pro.setLogo("proyecto_foto_default.jpg");
            interProyecto.saveProyecto(pro);
            return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("proyecto/borrar/{id}")
    public ResponseEntity<Proyecto> deleteProyecto(@PathVariable Long id){
        try {
            Proyecto pro = interProyecto.findProyecto(id);
            interProyecto.deleteProyecto(id);
            return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("proyecto/editar/{id}")
    public ResponseEntity<Proyecto> editProyecto(
            @PathVariable Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String desc,
            @RequestParam("desde") String desde,
            @RequestParam("hasta") String hasta,
            @RequestParam("enlace") String enlace,
            @RequestParam("sitio") String sitio){
        
        try {
            Proyecto pro = interProyecto.findProyecto(id);
            pro.setNombre(nombre);
            pro.setDescripcion(desc);
            pro.setDesde(desde);
            pro.setHasta(hasta);
            pro.setEnlace(enlace);
            pro.setSitio(sitio);
            interProyecto.saveProyecto(pro);
            return new ResponseEntity<>(pro, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //sin usar
    @GetMapping("proyecto/buscar/{id}")
    public Proyecto findProyecto(@PathVariable Long id){
        Proyecto pro = interProyecto.findProyecto(id);
        return pro;
    }
    
    //agregado para testeos
    @PutMapping ("proyecto/agregarImg/{id}")
    public ResponseEntity<Map> saveImagen(
            @PathVariable Long id,
            @RequestParam("img") MultipartFile img){
        try {
            BufferedImage bufferedImg = ImageIO.read(img.getInputStream());
            if (bufferedImg == null){ //si es nulo no es una imagen
                return new ResponseEntity(new Mensaje("El archivo no es una imagen!"), HttpStatus.BAD_REQUEST);
            }
            if (!interProyecto.existProyectoById(id)){
                return new ResponseEntity(new Mensaje("El proyecto indicado no existe!"), HttpStatus.NOT_FOUND);
            }
            Map result = cloudinaryService.upload(img);
            Imagen imagen = new Imagen("perfil_proyecto_"+id.hashCode()+".jpg", (String) result.get("url"), (String) result.get("public_id"));
            interProyecto.loadImage(imagen, id);
            return new ResponseEntity(new Mensaje("Imagen actualizada con exito!"), HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("Algo salio mal"), HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping ("proyecto/borrarImg/{id}")
    public ResponseEntity<Proyecto> deleteImagen(
            @PathVariable Long id //id de registro experiencia
        ){
        
        try{
            if (interProyecto.existProyectoById(id)){
                Proyecto pro = interProyecto.findProyecto(id);
                if (imagen_public_id != pro.getLogo_public_id()){
                    Map result = cloudinaryService.delete(pro.getLogo_public_id());
                    if (!result.isEmpty()){
                        pro.setLogo(imagen);
                        pro.setLogo_public_id(imagen_public_id);
                        pro.setLogo_url(imagen_url);
                        interProyecto.saveProyecto(pro);
                        return new ResponseEntity<> (pro, HttpStatus.OK);
                    } else {
                        return new ResponseEntity(new Mensaje("Problemas al intentar borrar la imagen."), HttpStatus.NOT_FOUND);
                    }
                } else {
                    pro.setLogo(imagen);
                    pro.setLogo_public_id(imagen_public_id);
                    pro.setLogo_url(imagen_url);
                    interProyecto.saveProyecto(pro);
                    return new ResponseEntity<> (pro, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity(new Mensaje("Proyecto no encontrado!"), HttpStatus.NOT_FOUND);
            }
        } catch (IOException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity(new Mensaje("Algo salio mal"), HttpStatus.NOT_FOUND);
        }     
    }
    
    //metodo para traer solo lo referente al usuario solicitado
    @GetMapping("proyecto/buscarByUsuario/{usuario}")
    public ResponseEntity<List<Proyecto>> findProyectoByUsuario(@PathVariable("usuario") String usuario){
        try {
            List<Proyecto> listaProyecto = interProyecto.getProyectosByUsuario(usuario);
            return new ResponseEntity<List<Proyecto>>(listaProyecto, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
