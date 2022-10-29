/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.model.Habilidad;
import com.apirest.portfolio.service.IHabilidadService;
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

/**
 *
 * @author PCcito
 */
@RestController
//@CrossOrigin(origins="http://localhost:4200")
@CrossOrigin(origins="https://app-portfolio-36e26.web.app")
public class HabilidadController {
    @Autowired
    private IHabilidadService interHabilidad;
    
    @GetMapping("habilidad/traer")
    public ResponseEntity<List<Habilidad>> getHabilidades(){
        try {
            List<Habilidad> hab = interHabilidad.getHabilidades();
            if (hab.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else {
                return new ResponseEntity<>(hab, HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("habilidad/crear")
    public ResponseEntity<Habilidad> createHabilidad(@RequestBody Habilidad hab){
        try {
            interHabilidad.saveHabilidad(hab);
            return new ResponseEntity<>(hab, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("habilidad/borrar/{id}")
    public ResponseEntity<Habilidad> deleteHabilidad(@PathVariable Long id){
        try {
            Habilidad hab = interHabilidad.findHabilidad(id);
            interHabilidad.deleteHabilidad(id);
            return new ResponseEntity<>(hab, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }
    
    @PutMapping("habilidad/editar/{id}")
    public ResponseEntity<Habilidad> editHabilidad(
            @PathVariable Long id,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("nivel") int nivel,
            @RequestParam("tipo_habilidad_id") Long tipo_habilidad_id){
        try {
            Habilidad hab = interHabilidad.findHabilidad(id);
            hab.setDescripcion(descripcion);
            hab.setNivel(nivel);
            hab.setTipo_habilidad_id(tipo_habilidad_id);
            interHabilidad.saveHabilidad(hab);
            return new ResponseEntity<>(hab, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //hecho por mi y ya esta funcionando
    @GetMapping("habilidad/traerById/{id}")
    public List<Habilidad> findHabilidadByUserId(@PathVariable Long id){
        List<Habilidad> lista = interHabilidad.getHabilidadesByUserId(id);
        return lista;
    }
    
    @GetMapping("habilidad/buscarByUsuario/{usuario}")
    public ResponseEntity<List<Habilidad>> bucarByUsuario(@PathVariable("usuario") String usuario){
        try{
            List<Habilidad> listaHabilidad = interHabilidad.getHabilidadesByUsuario(usuario);
            return new ResponseEntity<List<Habilidad>>(listaHabilidad, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
