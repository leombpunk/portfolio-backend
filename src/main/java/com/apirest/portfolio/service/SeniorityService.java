/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.model.Seniority;
import com.apirest.portfolio.repository.SeniorityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PCcito
 */
@Service
public class SeniorityService implements ISeniorityService {
    @Autowired
    private SeniorityRepository seniorRepository;
            
    @Override
    public List<Seniority> getSeniorities() {
        List<Seniority> senior = seniorRepository.findAll();
        return senior;
    }

    @Override
    public void saveSeniority(Seniority senior) {
        seniorRepository.save(senior);
    }

    @Override
    public void deleteSeniority(Long id) {
        seniorRepository.deleteById(id);
    }

    @Override
    public Seniority findSeniority(Long id) {
        Seniority senior = seniorRepository.findById(id).orElse(null);
        return senior;
    }
    
}
