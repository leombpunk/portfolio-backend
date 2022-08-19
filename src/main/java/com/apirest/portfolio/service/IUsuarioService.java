/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.service;

import com.apirest.portfolio.model.Usuario;
import java.util.List;

/**
 *
 * @author PCcito
 */
public interface IUsuarioService {
    public List<Usuario> getUsuarios();
    public void saveUsuario(Usuario usu);
    public void deleteUsuario(Long id);
    public Usuario findUsuario(Long id);
}
