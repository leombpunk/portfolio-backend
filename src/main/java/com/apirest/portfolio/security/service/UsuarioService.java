/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.security.service;

import com.apirest.portfolio.security.model.Usuario;
import com.apirest.portfolio.security.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author PCcito
 */
@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private UsuarioRepository usuRepository;

    @Override
    public List<Usuario> getUsuarios() {
        List<Usuario> listaUsuarios = usuRepository.findAll();
        return listaUsuarios;
    }

    @Override
    public void saveUsuario(Usuario usu) {
        usuRepository.save(usu);
    }

    @Override
    public void deleteUsuario(Long id) {
        usuRepository.deleteById(id);
    }

    @Override
    public Usuario findUsuario(Long id) {
        Usuario usu = usuRepository.findById(id).orElse(null);
        return usu;
    }
}
