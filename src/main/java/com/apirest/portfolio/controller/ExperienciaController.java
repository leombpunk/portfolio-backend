/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.model.Experiencia;
import com.apirest.portfolio.service.IExperienciaService;
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
public class ExperienciaController {
    @Autowired
    private IExperienciaService interExperiencia;
    
    @GetMapping("experiencia/traer")
    public List<Experiencia> getExperiencias(){
        return interExperiencia.getExperiencias();
    }
    
    @PostMapping("experiencia/crear")
    public String createExperiencia(@RequestBody Experiencia expe){
        interExperiencia.saveExperiencia(expe);
        return "Experiencia agregada correctamente.";
    }
    
    @DeleteMapping("experiencia/borrar/{id}")
    public String deleteExperiencia(@PathVariable Long id){
        interExperiencia.deleteExperiencia(id);
        return "Experiencia borrada correctamente.";
    }
    
    @PutMapping("experiencia/editar/{id}")
    public Experiencia editExperiencia(
            @PathVariable Long id,
            @RequestParam("cargo") String cargo,
            @RequestParam("tarea") String tarea,
            @RequestParam("desde") String desde,
            @RequestParam("hasta") String hasta,
            @RequestParam("logo") String logo,
            @RequestParam("empresa") String empresa,
            @RequestParam("reftelef") String reftelef,
            @RequestParam("refnombre") String refnombre){
        
        Experiencia expe = interExperiencia.findExperiencia(id);
        
        expe.setCargo(cargo);
        expe.setTarea(tarea);
        expe.setDesde(desde);
        expe.setHasta(hasta);
        expe.setLogo(logo);
        expe.setEmpresa(empresa);
        expe.setReftelef(reftelef);
        expe.setRefnombre(refnombre);
        
        return expe;
    }
    
    @GetMapping("experiencia/buscar/{id}")
    public Experiencia findExperiencia(@PathVariable Long id){
        Experiencia expe = interExperiencia.findExperiencia(id);
        return expe;
    }
}
