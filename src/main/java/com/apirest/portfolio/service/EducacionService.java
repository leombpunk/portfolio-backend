/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.model.Educacion;
import com.apirest.portfolio.repository.EducacionRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PCcito
 */
@Service
public class EducacionService implements IEducacionService {
    @Autowired
    private EducacionRepository eduRepository;
    
    @Override
    public List<Educacion> getEducaciones() {
        List<Educacion> listaEducacion = eduRepository.findAll();
        return listaEducacion;
    }

    @Override
    public void saveEducacion(Educacion edu) {
        eduRepository.save(edu);
    }

    @Override
    public void deleteEducacion(Long id) {
        eduRepository.deleteById(id);
    }

    @Override
    public Educacion findEducacion(Long id) {
        Educacion edu = eduRepository.findById(id).orElse(null);
        return edu;
    }

    @Override
    public byte[] getImage(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void loadImage(MultipartFile img, Long id) {
        //reviso que la imagen no este vacia
        Educacion edu = eduRepository.getById(id);
        if(!img.isEmpty()){
            //creo una variable con la ruta donde almacenare las imagenes
            Path directorioImagenes = Paths.get("src//main//resources//static/images");
            //creo una variable donde guardo la ruta absoluta
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
            
            //intento obtener los datos de la imagen en un try por si da error
            try {
                //creo una variable para la imagen
                byte[] bytesImg = img.getBytes();
                //creo el nombre de la imagen
                String nombreImagen = "educacion_foto_" + id.hashCode() + ".jpg";
                //establezco la ruta para guardar la imagen
                //Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + img.getOriginalFilename());
                Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + nombreImagen);
                //al crear el nombre de la imagen verificar si esta existe,
                //si existe la borrare.
                Files.deleteIfExists(rutaCompleta);
                //guardo la imagen en la ruta establecida
                Files.write(rutaCompleta, bytesImg);
                
                //debe decidir si delegar el guardado de la imagen aqui o
                //como procedo
                edu.setLogo(nombreImagen);
                eduRepository.save(edu);
                
                //crear un nombre para cada imagen en el siguietne formato propuesto
                //perfil-01-02.jpg -> primero el nombre de la clase a la que pertenece
                //liego el id del usuario y por ultimo el id del perfil y su repectivo
                //nombre de extension
                
            } catch (IOException ex) {
                Logger.getLogger(PerfilService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void updateImage(MultipartFile img, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteImage(String img, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
