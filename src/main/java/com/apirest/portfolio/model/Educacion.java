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
@Entity(name="educacion")
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="institucion")
    private String institucion;
    @Column(name="locacion")
    private String locacion;
    @Column(name="habilidades")
    private String habilidades;
    @Column(name="logo")
    private String logo;
    //nuevos atributos
    @Column(name="logo_url")
    private String logo_url;
    @Column(name="logo_public_id")
    private String logo_public_id;
    //hasta aqui
    @Column(name="titulo")
    private String titulo;
    @Column(name="desde")
    private String desde;
    @Column(name="hasta")
    private String hasta;
    @Column(name="usuarios_id")
    private Long usuarios_id;
}
