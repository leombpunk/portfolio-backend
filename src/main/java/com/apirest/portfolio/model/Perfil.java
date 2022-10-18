/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apirest.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author PCcito
 */
@Getter @Setter
@Entity(name="perfiles")
public class Perfil {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="titulo")
    private String titulo;
    @Column(name="correo")
    private String correo;
    @Column(name="linkedin")
    private String linkedin;
    @Column(name="github")
    private String github;
    @Column(name="foto")
    private String foto;
    //nuevos atributos
    @Column(name="foto_url")
    private String foto_url;
    @Column(name="foto_public_id")
    private String foto_public_id;
    //hasta aqui
    @Column(name="acercade")
    private String acercade;
    @Column(name="usuarios_id")
    private Long usuarios_id;
}
