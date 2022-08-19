/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.model.Educacion;
import com.apirest.portfolio.service.IEducacionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public String createEducacion(@RequestBody Educacion edu){
        interEducacion.saveEducacion(edu);
        return "Los datos de educacion se guardaron correctamente.";
    }
    
    @DeleteMapping("educacion/borrar/{id}")
    public String deleteEducacion(@PathVariable Long id){
        interEducacion.deleteEducacion(id);
        return "Los datos de educacion se ha borrado correctamente.";
    }
    
    @PutMapping("educacion/editar/{id}")
    public Educacion editEducacion(
            @PathVariable Long id,
            @RequestParam("institucion") String institucion,
            @RequestParam("titulo") String titulo,
            @RequestParam("locacion") String locacion,
            @RequestParam("habilidades") String habilidades,
            @RequestParam("logo") String logo,
            @RequestParam("desde") String desde,
            @RequestParam("hasta") String hasta){
        
        Educacion edu = interEducacion.findEducacion(id);
        edu.setInstitucion(institucion);
        edu.setDesde(desde);
        edu.setHasta(hasta);
        edu.setLogo(logo);
        edu.setTitulo(titulo);
        edu.setHabilidades(habilidades);
        edu.setLocacion(locacion);
        
        return edu;
    }
    
    @GetMapping("educacion/buscar/{id}")
    public Educacion findEducacion(@PathVariable Long id){
        return interEducacion.findEducacion(id);
    }
}
