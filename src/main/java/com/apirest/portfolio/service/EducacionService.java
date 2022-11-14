/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.dto.Imagen;
import com.apirest.portfolio.model.Educacion;
import com.apirest.portfolio.repository.EducacionRepository;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author PCcito
 */
@Service
public class EducacionService implements IEducacionService, IValidarFechaService {
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
    public void loadImage(Imagen img, Long id) {
        Educacion edu = eduRepository.getById(id);
        edu.setLogo(img.getFoto_nombre());
        edu.setLogo_public_id(img.getFoto_public_id());
        edu.setLogo_url(img.getFoto_url());
        eduRepository.save(edu);
    }

    @Override
    public List<Educacion> getEducacionByUsuario(String usuario) {
        List<Educacion> listaEducacion = eduRepository.listaEducacionByUsuario(usuario);
        return listaEducacion;
    }

    @Override
    public Boolean existEducacionById(Long id) {
        return eduRepository.existsById(id);
    }

    @Override
    public boolean isValidDate(String fecha) {
        Pattern pattern = Pattern.compile("^(\\d{4}(\\/|-)(0[1-9]|1[0-2])\\2([0-2][0-9]|3[0-1]))$");
        Matcher match = pattern.matcher(fecha);
        if (!match.matches()){
            return false;
        }
        else if (!GenericValidator.isDate(fecha, "yyyy-MM-dd", true)){
            return false;
        }
        return true;
    }
}
