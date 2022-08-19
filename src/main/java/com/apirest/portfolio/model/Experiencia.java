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
@Entity(name="experiencias")
public class Experiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="cargo")
    private String cargo;
    @Column(name="tareas")
    private String tarea;
    @Column(name="desde")
    private String desde;
    @Column(name="hasta")
    private String hasta;
    @Column(name="logo")
    private String logo;
    @Column(name="empresa")
    private String empresa;
    @Column(name="referenciatel")
    private String reftelef;
    @Column(name="referencianombre")
    private String refnombre;
    @Column(name="usuarios_id")
    private Long usuarios_id;
}
