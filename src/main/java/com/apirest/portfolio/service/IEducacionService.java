/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.dto.Imagen;
import com.apirest.portfolio.model.Educacion;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PCcito
 */
public interface IEducacionService {
    public List<Educacion> getEducaciones();
    public void saveEducacion(Educacion edu);
    public void deleteEducacion(Long id);
    public Educacion findEducacion(Long id);
    
    //prueba para imagenes
    public byte[] getImage(Long id); //or name image
    public void loadImage(Imagen img, Long id);
    public void updateImage(MultipartFile img, Long id);
    public void deleteImage(String img, Long id);
    
    //nuevo metodo
    //interfaz para @Query personalizada
    public List<Educacion> getEducacionByUsuario(String usuario);
    
    public Boolean existEducacionById(Long id);
}
