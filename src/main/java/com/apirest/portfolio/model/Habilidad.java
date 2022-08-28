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
@Entity(name="habilidades")
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="descripcion")
    private String descripcion;
    @Column(name="nivel")
    private int nivel;
    @Column(name="usuarios_id")
    private Long usuarios_id;
    @Column(name="tipo_habilidad_id")
    private Long tipo_habilidad_id;
}
