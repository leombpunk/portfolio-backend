/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.model.Proyecto;
import com.apirest.portfolio.service.IProyectoService;
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
public class ProyectoController {
    @Autowired 
    private IProyectoService interProyecto;
    
    @GetMapping("proyecto/traer")
    public List<Proyecto> getProyectos(){
        return interProyecto.getProyectos();
    }
    
    @PostMapping("proyecto/crear")
    public String createProyecto(@RequestBody Proyecto pro){
        interProyecto.saveProyecto(pro);
        return "Proyecto guardado correctamente.";
    }
    
    @DeleteMapping("proyecto/borrar/{id}")
    public String deleteProyecto(@PathVariable Long id){
        interProyecto.deleteProyecto(id);
        return "Proyecto borrado correctamente.";
    }
    
    @PutMapping("proyecto/editar/{id}")
    public Proyecto editProyecto(
            @PathVariable Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("descripcion") String desc,
            @RequestParam("fecha") String fecha,
            @RequestParam("enlace") String enlace,
            @RequestParam("sitio") String sitio){
        
        Proyecto pro = interProyecto.findProyecto(id);
        pro.setNombre(nombre);
        pro.setDescripcion(desc);
        pro.setFecha(fecha);
        pro.setEnlace(enlace);
        pro.setSitio(sitio);;
        
        return pro;
    }
    
    @GetMapping("proyecto/buscar/{id}")
    public Proyecto findProyecto(@PathVariable Long id){
        Proyecto pro = interProyecto.findProyecto(id);
        return pro;
    }
}
