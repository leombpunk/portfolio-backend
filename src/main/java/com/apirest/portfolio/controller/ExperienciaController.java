/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.model.Experiencia;
import com.apirest.portfolio.service.IExperienciaService;
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
public class ExperienciaController {
    @Autowired
    private IExperienciaService interExperiencia;
    
    @GetMapping("experiencia/traer")
    public List<Experiencia> getExperiencias(){
        return interExperiencia.getExperiencias();
    }
    
    @PostMapping("experiencia/crear")
    public ResponseEntity<Experiencia> createExperiencia(@RequestBody Experiencia expe){
        try {
            expe.setLogo("experiencia_foto_default.jpg");
            //System.out.print(expe);
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
    
    @GetMapping("experiencia/buscar/{id}")
    public Experiencia findExperiencia(@PathVariable Long id){
        Experiencia expe = interExperiencia.findExperiencia(id);
        return expe;
    }
    
    //agregado para testeos
    @PutMapping ("experiencia/agregarImg/{id}")
    public String saveImagen(
            @PathVariable Long id,
            @RequestParam("img") MultipartFile img){
        interExperiencia.loadImage(img, id);
        return "{ \"status\": \"ok\" }";
    }
    
    @DeleteMapping ("experiencia/borrarImg/{id}")
    public ResponseEntity<Experiencia> deleteImagen(
            @PathVariable("id") Long id //id de registro experiencia
        ){
        
        try{
            Experiencia expe = interExperiencia.findExperiencia(id);
            expe.setLogo("experiencia_foto_default.jpg");
            interExperiencia.saveExperiencia(expe);
            return new ResponseEntity<> (expe, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
