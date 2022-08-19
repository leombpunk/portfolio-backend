/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.model.Perfil;
import com.apirest.portfolio.service.IPerfilService;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
public class PerfilController {
    @Autowired
    private IPerfilService interPerfil;
    
    @GetMapping ("perfil/traer")
    public List<Perfil> getPerfiles(){
        return interPerfil.getPerfiles();
    }
    
    @PostMapping ("perfil/crear")
    public String createPerfil(@RequestBody Perfil perf){
        interPerfil.savePerfil(perf);
        return "El perfil se creo correctamente.";
    }
    
    @DeleteMapping ("perfil/borrar/{id}")
    public String deletePerfil (@PathVariable Long id){
        interPerfil.deletePerfil(id);
        return "El perfil ha sido borrado correctamente.";
    }
    
    @PutMapping ("perfil/editar/{id}")
    public ResponseEntity<Perfil> editPerfil (
            @PathVariable Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("titulo") String titulo,
            @RequestParam("correo") String correo,
            @RequestParam("linkedin") String linkedin,
            @RequestParam("github") String github,
            @RequestParam("acercade") String acercade
    ){
        
        try {
            Perfil perf = interPerfil.findPerfil(id);
            perf.setNombre(nombre);
            perf.setAcercade(acercade);
            perf.setApellido(apellido);
            perf.setTitulo(titulo);
            //perf.setFoto(foto);
            perf.setGithub(github);
            perf.setCorreo(correo);
            perf.setLinkedin(linkedin);
            interPerfil.savePerfil(perf);
            return new ResponseEntity<>(perf, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping ("perfil/buscar/{id}")
    public Perfil findPerfil(@PathVariable Long id){
        Perfil perf = interPerfil.findPerfil(id);
        return perf;
    }
    
    //agregado para testeos
    //@GetMapping("perfil/agregarImg/{id}")
    @PutMapping ("perfil/agregarImg/{id}")
    public String saveImagen(
            @PathVariable Long id,
            @RequestParam("img") MultipartFile img){
        interPerfil.loadImage(img, id);
        return "{ \"status\": \"ok\" }";
    }
    
    @DeleteMapping ("perfil/borrarImg/{id}")
    public ResponseEntity<Perfil> deleteImagen(
            @PathVariable Long id //id del perfil
        ){
        
        try{
            Perfil perf = interPerfil.findPerfil(id);
            perf.setFoto("perfil_foto_default.jpg");
            interPerfil.savePerfil(perf);
            return new ResponseEntity<> (perf, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }    
    }
}
