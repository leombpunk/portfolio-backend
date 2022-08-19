/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apirest.portfolio.repository;

import com.apirest.portfolio.model.Habilidad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author PCcito
 */
@Repository
public interface HabilidadRepository extends JpaRepository<Habilidad, Long> {
    @Query("select h from habilidades h where h.usuarios_id = :usuarios_id")
    List<Habilidad> listaHabilidadesByUserId(@Param("usuarios_id") Long usuarios_id);
}
