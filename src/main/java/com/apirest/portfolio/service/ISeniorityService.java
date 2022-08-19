/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.model.Seniority;
import java.util.List;

/**
 *
 * @author PCcito
 */
public interface ISeniorityService {
    public List<Seniority> getSeniorities();
    public void saveSeniority(Seniority senior);
    public void deleteSeniority(Long id);
    public Seniority findSeniority(Long id);
}
