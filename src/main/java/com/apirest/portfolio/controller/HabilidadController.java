/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.model.Habilidad;
import com.apirest.portfolio.service.IHabilidadService;
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
public class HabilidadController {
    @Autowired
    private IHabilidadService interHabilidad;
    
    @GetMapping("habilidad/traer")
    public List<Habilidad> getHabilidades(){
        return interHabilidad.getHabilidades();
    }
    
    @PostMapping("habilidad/crear")
    public String createHabilidad(@RequestBody Habilidad hab){
        interHabilidad.saveHabilidad(hab);
        return "Habilidad agregada correctamente.";
    }
    
    @DeleteMapping("habilidad/borrar/{id}")
    public String deleteHabilidad(@PathVariable Long id){
        interHabilidad.deleteHabilidad(id);
        return "Habilidad borrada correctamente.";
    }
    
    @PutMapping("habilidad/editar/{id}")
    public Habilidad editHabilidad(
            @PathVariable Long id,
            @RequestParam("descripcion") String desc,
            @RequestParam("nivel") int nivel,
            @RequestParam("seniority") Long seniority,
            @RequestParam("habilidadTipo") Long habilidadTipo){
        Habilidad hab = interHabilidad.findHabilidad(id);
        hab.setDesc(desc);
        hab.setNivel(nivel);
        hab.setSeniority_id(seniority);
        hab.setTipo_habilidad_id(habilidadTipo);
        
        return hab;
    }
    
    //hecho por mi y ya esta funcionando
    @GetMapping("habilidad/traerById/{id}")
    public List<Habilidad> findHabilidadByUserId(@PathVariable Long id){
        List<Habilidad> lista = interHabilidad.getHabilidadesByUserId(id);
        return lista;
    }
}
