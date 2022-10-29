/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.cloudinary.service.CloudinaryService;
import com.apirest.portfolio.dto.Imagen;
import com.apirest.portfolio.dto.Mensaje;
import com.apirest.portfolio.model.Educacion;
import com.apirest.portfolio.service.IEducacionService;
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
public class EducacionController {
    @Autowired
    private IEducacionService interEducacion;
    
    @Autowired
    private CloudinaryService cloudinaryService;
    
    @Value("${image.default.educacion.nombre}")
    private String imagen;
    
    @Value("${image.default.educacion.public.id}")
    private String imagen_public_id;
    
    @Value("${image.default.educacion.url}")
    private String imagen_url;
    
    @GetMapping("educacion/traer")
    public List<Educacion> getEducaciones(){
        return interEducacion.getEducaciones();
    }
    
    @PostMapping("educacion/crear")
    public ResponseEntity<Educacion> createEducacion(@RequestBody Educacion edu){
        try {
            edu.setLogo(imagen);
            edu.setLogo_public_id(imagen_public_id);
            edu.setLogo_url(imagen_url);
            interEducacion.saveEducacion(edu);
            return new ResponseEntity<>(edu, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("educacion/borrar/{id}")
    public ResponseEntity<Educacion> deleteEducacion(@PathVariable Long id){
        try {
            Educacion edu = interEducacion.findEducacion(id);
            interEducacion.deleteEducacion(id);
            return new ResponseEntity<>(edu, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("educacion/editar/{id}")
    public ResponseEntity<Educacion> editEducacion(
            @PathVariable Long id,
            @RequestParam("institucion") String institucion,
            @RequestParam("titulo") String titulo,
            @RequestParam("locacion") String locacion,
            @RequestParam("habilidades") String habilidades,
            //@RequestParam("logo") String logo,
            @RequestParam("desde") String desde,
            @RequestParam("hasta") String hasta){
        
        try{
            Educacion edu = interEducacion.findEducacion(id);
            edu.setInstitucion(institucion);
            edu.setDesde(desde);
            edu.setHasta(hasta);
            //edu.setLogo(logo);
            edu.setTitulo(titulo);
            edu.setHabilidades(habilidades);
            edu.setLocacion(locacion);
            interEducacion.saveEducacion(edu);
            return new ResponseEntity<>(edu, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //sin usar
    @GetMapping("educacion/buscar/{id}")
    public Educacion findEducacion(@PathVariable Long id){
        return interEducacion.findEducacion(id);
    }
    
    //agregado para testeos
    @PutMapping ("educacion/agregarImg/{id}")
    public ResponseEntity<Map> saveImagen(
            @PathVariable Long id,
            @RequestParam("img") MultipartFile img){
        try {
            BufferedImage bufferedImg = ImageIO.read(img.getInputStream());
            if (bufferedImg == null){ //si es nulo no es una imagen
                return new ResponseEntity(new Mensaje("El archivo no es una imagen!"), HttpStatus.BAD_REQUEST);
            }
            if (!interEducacion.existEducacionById(id)){
                return new ResponseEntity(new Mensaje("El estudio/curso indicado no existe!"), HttpStatus.NOT_FOUND);
            }
            Map result = cloudinaryService.upload(img);
            Imagen imagen = new Imagen("perfil_educacion_"+id.hashCode(), (String) result.get("url"), (String) result.get("public_id"));
            interEducacion.loadImage(imagen, id);
            return new ResponseEntity(new Mensaje("Imagen actualizada con exito!"), HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity(new Mensaje("Algo salio mal"), HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping ("educacion/borrarImg/{id}")
    public ResponseEntity<Educacion> deleteImagen(
            @PathVariable Long id //id de registro experiencia
        ){
        
        try{
            if (interEducacion.existEducacionById(id)){
                Educacion edu = interEducacion.findEducacion(id);
                if (imagen_public_id != edu.getLogo_public_id()){
                    Map result = cloudinaryService.delete(edu.getLogo_public_id());
                    if (!result.isEmpty()){
                        edu.setLogo(imagen);
                        edu.setLogo_public_id(imagen_public_id);
                        edu.setLogo_url(imagen_url);
                        interEducacion.saveEducacion(edu);
                        return new ResponseEntity<> (edu, HttpStatus.OK);
                    } else {
                        return new ResponseEntity(new Mensaje("Problemas al intentar borrar la imagen."), HttpStatus.NOT_FOUND);
                    }
                } else {
                    edu.setLogo(imagen);
                    edu.setLogo_public_id(imagen_public_id);
                    edu.setLogo_url(imagen_url);
                    interEducacion.saveEducacion(edu);
                    return new ResponseEntity<> (edu, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity(new Mensaje("Estudio/Curso no encontrada!"), HttpStatus.NOT_FOUND);
            }
        } catch (IOException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity(new Mensaje("Algo salio mal"), HttpStatus.NOT_FOUND);
        }       
    }
    
    @GetMapping("educacion/buscarByUsuario/{usuario}")
    public ResponseEntity<List<Educacion>> buscarByUsuario(@PathVariable("usuario") String usuario){
        try{
            List<Educacion> listaEducacion = interEducacion.getEducacionByUsuario(usuario);
            return new ResponseEntity<List<Educacion>>(listaEducacion, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
