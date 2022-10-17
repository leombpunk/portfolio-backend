/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.controller;

import com.apirest.portfolio.cloudinary.service.CloudinaryService;
import com.apirest.portfolio.dto.Imagen;
import com.apirest.portfolio.dto.Mensaje;
import com.apirest.portfolio.model.Perfil;
import com.apirest.portfolio.service.IPerfilService;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    
    //injectar el servicio de cloudinary creado
    @Autowired
    private CloudinaryService cloudinaryService;
    
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
    public String deletePerfil (@PathVariable("id") Long id){
        interPerfil.deletePerfil(id);
        return "El perfil ha sido borrado correctamente.";
    }
    
    @PreAuthorize("hasRole('USER')") // para que el administrador sea el que pueda editar el resto de roles no
    @PutMapping ("perfil/editar/{id}")
    public ResponseEntity<Perfil> editPerfil (
            @PathVariable("id") Long id,
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
    public Perfil findPerfil(@PathVariable("id") Long id){
        Perfil perf = interPerfil.findPerfil(id);
        return perf;
    }
    
    @PutMapping ("perfil/agregarImg/{id}")
    public ResponseEntity<Map> saveImagen(
            @PathVariable("id") Long id,
            @RequestParam("img") MultipartFile img){
  
        try {
            //verificar que sea una imagen lo que recibe el endpoint
            BufferedImage bufferedImg = ImageIO.read(img.getInputStream());
            if (bufferedImg == null){ //si es nulo no es una imagen
                return new ResponseEntity(new Mensaje("El archivo no es una imagen!"), HttpStatus.BAD_REQUEST);
            }
            
            //inicio de codigo a testear
            //uso el servicio para verificar si el perfil existe
            //si no existe lanza una exception al intentar asignar el resultado
            //y asi evita que se ejecute el servicio de cloudinary y la imagen no sube
            Perfil perf = interPerfil.findPerfil(id);
            //System.out.print("perfil id: "+perf.getId().toString());
            if (perf.getId().toString() == ""){
                System.out.print("esto no funciona si el id del perfil no existe, es un absurdo porque el id es Long");
            }
            //fin de codigo a testear
            
            //tambien puedo usar el servicio de perfil para buscar el perfil por el id, si devuelve un registro procedo a hacer el resto
            Map result = cloudinaryService.upload(img);
            //una vez que el servicio sube la imagen verificar el response
            //y con los pares public_id y format armar el nombre de la imagen
            //verificar el par url
            //System.out.print(result.get("public_id"));
            Imagen imagen = new Imagen("perfil_foto_"+id.hashCode()+".jpg", (String) result.get("url"), (String) result.get("public_id"));
            interPerfil.loadImage(imagen, id);
            return new ResponseEntity(null, HttpStatus.OK);
        } catch (IOException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity(new Mensaje("Algo salio mal"), HttpStatus.NOT_FOUND);
        }  
    }
    
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping ("perfil/borrarImg/{id}")
    public ResponseEntity<Perfil> deleteImagen(
            @PathVariable("id") Long id //id del perfil
        ){
        
        try{
            //hacer lo mismo que en el metodo saveImagen
            Perfil perf = interPerfil.findPerfil(id);
            Map result = cloudinaryService.delete(perf.getFoto());
            if (!result.isEmpty()){
                perf.setFoto("perfil_foto_default.jpg");
                interPerfil.savePerfil(perf);
                return new ResponseEntity<>(perf, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            
        } catch (IOException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }     
    }
    
    @GetMapping("perfil/buscarByUsuario/{usuario}")
    public ResponseEntity<Perfil> buscarByUsuario(@PathVariable("usuario") String usuario){
        try{
            Perfil perfil = interPerfil.buscarPerilByUsuario(usuario);
            return new ResponseEntity<Perfil>(perfil, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
