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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    
    @NotNull
    @NotBlank
    @Size(min=3, max=50)
    @Pattern(regexp="^[a-zA-Z0-9\\sÁáÉéÍíÓóÚúÑñÜü]+$")
    @Column(name="institucion")
    private String institucion;
    
    @NotNull
    @NotBlank
    @Size(min=3, max=50)
    @Pattern(regexp="^[a-zA-Z\\d\\s]*\\-[a-zA-Z\\d\\s]*\\-[a-zA-Z\\d\\s]+$")
    @Column(name="locacion")
    private String locacion;
    
    @NotNull
    @NotBlank
    @Size(min=5, max=500)
    @Pattern(regexp="^[a-zA-Z0-9\\sÁáÉéÍíÓóÚúÑñÜü/.,;:()\"'\\-]+$")
    @Column(name="habilidades")
    private String habilidades;
    
    //los campos relacionados con logo a mi parecer no necesitarian validarse
    //ya que un usuario no puede modificar estos datos de ningun modo, se modifian
    //programaticamente, tambien segun los datos devueltos por es servicio de
    //cloudinaty
    @Size(max=50)
    @Column(name="logo")
    private String logo;
    
    @Size(max=100)
    @Column(name="logo_url")
    private String logo_url;
    
    @Size(max=50)
    @Column(name="logo_public_id")
    private String logo_public_id;
    
    @NotNull
    @NotBlank
    @Size(min=3, max=50)
    @Pattern(regexp="^[a-zA-Z\\sÁáÉéÍíÓóÚúÑñÜü]+$")
    @Column(name="titulo")
    private String titulo;
    
    @NotNull
    @NotBlank
    @Size(min=10, max=10)
    @Pattern(regexp="^(\\d{4}(\\/|-)(0[1-9]|1[0-2])\\2([0-2][0-9]|3[0-1]))$")
    @Column(name="desde")
    private String desde;
    
    
    @Column(name="hasta")
    private String hasta;
    
    @NotNull
    @NotBlank
    @Size(min=1, max=10)
    @Pattern(regexp="^[0-9]+$")
    @Column(name="usuarios_id")
    private Long usuarios_id;
}
