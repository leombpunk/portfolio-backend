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
@Entity(name="proyectos")
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="desde")
    private String desde;
    @Column(name="hasta")
    private String hasta;
    @Column(name="logo")
    private String logo;
    //nuevos atributos
    @Column(name="logo_url")
    private String logo_url;
    @Column(name="logo_public_id")
    private String logo_public_id;
    //hasta aqui
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="enlace")
    private String enlace;
    @Column(name="sitio")
    private String sitio;
    @Column(name="usuarios_id")
    private Long usuarios_id;
}
