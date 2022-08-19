/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.model.Experiencia;
import java.util.List;

/**
 *
 * @author PCcito
 */
public interface IExperienciaService {
    public List<Experiencia> getExperiencias();
    public void saveExperiencia(Experiencia expe);
    public void deleteExperiencia(Long id);
    public Experiencia findExperiencia(Long id);
}
