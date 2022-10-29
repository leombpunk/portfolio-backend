/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.cloudinary.service.CloudinaryService;
import com.apirest.portfolio.dto.Imagen;
import com.apirest.portfolio.dto.Mensaje;
import com.apirest.portfolio.model.Experiencia;
import com.apirest.portfolio.service.IExperienciaService;
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
public class ExperienciaController {
    @Autowired
    private IExperienciaService interExperiencia;
    
    @Autowired
    private CloudinaryService cloudinaryService;
    
    @Value("${image.default.experiencia.nombre}")
    private String imagen;
    
    @Value("${image.default.experiencia.public.id}")
    private String imagen_public_id;
    
    @Value("${image.default.experiencia.url}")
    private String imagen_url;
    
    @GetMapping("experiencia/traer")
    public List<Experiencia> getExperiencias(){
        return interExperiencia.getExperiencias();
    }
    
    @PostMapping("experiencia/crear")
    public ResponseEntity<Experiencia> createExperiencia(@RequestBody Experiencia expe){
        try {
            expe.setLogo(imagen);
            expe.setLogo_public_id(imagen_public_id);
            expe.setLogo_url(imagen_url);
            interExperiencia.saveExperiencia(expe);
            return new ResponseEntity<>(expe, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("experiencia/borrar/{id}")
    public ResponseEntity<Experiencia> deleteExperiencia(@PathVariable Long id){
        try{
            Experiencia expe = interExperiencia.findExperiencia(id);
            interExperiencia.deleteExperiencia(id);
            return new ResponseEntity<>(expe, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("experiencia/editar/{id}")
    public ResponseEntity<Experiencia> editExperiencia(
            @PathVariable Long id,
            @RequestParam("cargo") String cargo,
            @RequestParam("tarea") String tarea,
            @RequestParam("desde") String desde,
            @RequestParam("hasta") String hasta,
            //@RequestParam("logo") String logo,
            @RequestParam("empresa") String empresa,
            @RequestParam("reftelef") String reftelef,
            @RequestParam("refnombre") String refnombre){
        
        try {
            Experiencia expe = interExperiencia.findExperiencia(id);
        
            expe.setCargo(cargo);
            expe.setTarea(tarea);
            expe.setDesde(desde);
            expe.setHasta(hasta);
            //expe.setLogo(logo);
            expe.setEmpresa(empresa);
            expe.setReftelef(reftelef);
            expe.setRefnombre(refnombre);
            interExperiencia.saveExperiencia(expe);

            return new ResponseEntity<>(expe, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        
    }
    
    //sin usar
    @GetMapping("experiencia/buscar/{id}")
    public Experiencia findExperiencia(@PathVariable Long id){
        Experiencia expe = interExperiencia.findExperiencia(id);
        return expe;
    }
    
    //agregado para testeos
    @PutMapping ("experiencia/agregarImg/{id}")
    public ResponseEntity<Map> saveImagen(
            @PathVariable Long id,
            @RequestParam("img") MultipartFile img){
        try {
            BufferedImage bufferedImg = ImageIO.read(img.getInputStream());
            if (bufferedImg == null){ //si es nulo no es una imagen
                return new ResponseEntity(new Mensaje("El archivo no es una imagen!"), HttpStatus.BAD_REQUEST);
            }
            if (!interExperiencia.existExperienciaById(id)){
                return new ResponseEntity(new Mensaje("La experiencia laboral indicada no existe!"), HttpStatus.NOT_FOUND);
            }
            Map result = cloudinaryService.upload(img);
            Imagen imagen = new Imagen("perfil_experiencia_"+id.hashCode(), (String) result.get("url"), (String) result.get("public_id"));
            interExperiencia.loadImage(imagen, id);
            return new ResponseEntity(new Mensaje("Imagen actualizada con exito!"), HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("Algo salio mal"), HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping ("experiencia/borrarImg/{id}")
    public ResponseEntity<Experiencia> deleteImagen(
            @PathVariable("id") Long id //id de registro experiencia
        ){
        
        try{
            if (interExperiencia.existExperienciaById(id)){
                Experiencia expe = interExperiencia.findExperiencia(id);
                if (imagen_public_id != expe.getLogo_public_id()){
                    Map result = cloudinaryService.delete(expe.getLogo_public_id());
                    if (!result.isEmpty()){
                        expe.setLogo(imagen);
                        expe.setLogo_public_id(imagen_public_id);
                        expe.setLogo_url(imagen_url);
                        interExperiencia.saveExperiencia(expe);
                        return new ResponseEntity<> (expe, HttpStatus.OK);
                    } else {
                        return new ResponseEntity(new Mensaje("Problemas al intentar borrar la imagen."), HttpStatus.NOT_FOUND);
                    }
                } else {
                    expe.setLogo(imagen);
                    expe.setLogo_public_id(imagen_public_id);
                    expe.setLogo_url(imagen_url);
                    interExperiencia.saveExperiencia(expe);
                    return new ResponseEntity<> (expe, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity(new Mensaje("Experiencia laboral no encontrada!"), HttpStatus.NOT_FOUND);
            }
        } catch (IOException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity(new Mensaje("Algo salio mal "+e.getMessage()), HttpStatus.NOT_FOUND);
        }     
    }
    
    @GetMapping("experiencia/buscarByUsuario/{usuario}")
    public ResponseEntity<List<Experiencia>> bucarByUsuario(@PathVariable("usuario") String usuario){
        try {
            List<Experiencia> listaExperiencia = interExperiencia.getExperienciaByUsuario(usuario);
            return new ResponseEntity<List<Experiencia>>(listaExperiencia, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
