/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.model.Perfil;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PCcito
 */
public interface IPerfilService {
    public List<Perfil> getPerfiles();
    public void savePerfil(Perfil perf);
    public void deletePerfil(Long id);
    public Perfil findPerfil(Long id);
    
    //prueba para imagenes
    public byte[] getImage(Long id); //or name image
    public void loadImage(MultipartFile img, Long id);
    public void updateImage(MultipartFile img, Long id);
    public void deleteImage(String img, Long id);
}