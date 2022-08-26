/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.model.Educacion;
import com.apirest.portfolio.service.IEducacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin(origins="http://localhost:4200")
public class EducacionController {
    @Autowired
    private IEducacionService interEducacion;
    
    @GetMapping("educacion/traer")
    public List<Educacion> getEducaciones(){
        return interEducacion.getEducaciones();
    }
    
    @PostMapping("educacion/crear")
    public ResponseEntity<Educacion> createEducacion(@RequestBody Educacion edu){
        try {
            edu.setLogo("educacion_foto_default.jpg");
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
    
    @GetMapping("educacion/buscar/{id}")
    public Educacion findEducacion(@PathVariable Long id){
        return interEducacion.findEducacion(id);
    }
    
    //agregado para testeos
    @PutMapping ("educacion/agregarImg/{id}")
    public String saveImagen(
            @PathVariable Long id,
            @RequestParam("img") MultipartFile img){
        interEducacion.loadImage(img, id);
        return "{ \"status\": \"ok\" }";
    }
    
    @DeleteMapping ("educacion/borrarImg/{id}")
    public ResponseEntity<Educacion> deleteImagen(
            @PathVariable Long id //id de registro experiencia
        ){
        
        try{
            Educacion edu = interEducacion.findEducacion(id);
            edu.setLogo("educacion_foto_default.jpg");
            interEducacion.saveEducacion(edu);
            return new ResponseEntity<> (edu, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }    
    }
}
