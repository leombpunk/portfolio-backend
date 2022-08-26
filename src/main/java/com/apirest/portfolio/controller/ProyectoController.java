/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.model.Proyecto;
import com.apirest.portfolio.service.IProyectoService;
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
public class ProyectoController {
    @Autowired 
    private IProyectoService interProyecto;
    
    @GetMapping("proyecto/traer")
    public List<Proyecto> getProyectos(){
        return interProyecto.getProyectos();
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
    
    @GetMapping("proyecto/buscar/{id}")
    public Proyecto findProyecto(@PathVariable Long id){
        Proyecto pro = interProyecto.findProyecto(id);
        return pro;
    }
    
    //agregado para testeos
    @PutMapping ("proyecto/agregarImg/{id}")
    public String saveImagen(
            @PathVariable Long id,
            @RequestParam("img") MultipartFile img){
        interProyecto.loadImage(img, id);
        return "{ \"status\": \"ok\" }";
    }
    
    @DeleteMapping ("proyecto/borrarImg/{id}")
    public ResponseEntity<Proyecto> deleteImagen(
            @PathVariable Long id //id de registro experiencia
        ){
        
        try{
            Proyecto pro = interProyecto.findProyecto(id);
            pro.setLogo("proyecto_foto_default.jpg");
            interProyecto.saveProyecto(pro);
            return new ResponseEntity<> (pro, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }    
    }
}
