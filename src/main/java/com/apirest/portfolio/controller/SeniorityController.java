/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.model.Seniority;
import com.apirest.portfolio.service.ISeniorityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SeniorityController {
    @Autowired
    private ISeniorityService interSeniority;
    
    @GetMapping("seniority/traer")
    public List<Seniority> getSeniorities(){
        return interSeniority.getSeniorities();
    }
    
    @PostMapping("seniority/crear")
    public String createSeniority(@RequestBody Seniority senior){
        interSeniority.saveSeniority(senior);
        return "Seniority creado correctamente.";
    }
    
    @DeleteMapping("seniority/borrar/{id}")
    public String deleteSeniority(@PathVariable Long id){
        interSeniority.deleteSeniority(id);
        return "Seniority ha sido borrado correctamente.";
    }
    
    @PutMapping("seniority/editar/{id}")
    public Seniority editSeniority(
            @PathVariable Long id,
            @RequestParam("nombre") String nombre){
        
        Seniority senior = interSeniority.findSeniority(id);
        senior.setNombre(nombre);
        
        return senior;
    }
    
    @GetMapping("seniority/buscar/{id}")
    public Seniority findSeniority(@PathVariable Long id){
        Seniority senior = interSeniority.findSeniority(id);
        return senior;
    }
}
