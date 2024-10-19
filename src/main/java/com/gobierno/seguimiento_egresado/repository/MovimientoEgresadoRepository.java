package com.gobierno.seguimiento_egresado.repository;

import com.gobierno.seguimiento_egresado.entity.MovimientoEgresado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovimientoEgresadoRepository extends JpaRepository<MovimientoEgresado, Long> {

    List<MovimientoEgresado> findByEgresadoId(Long egresadoId);

}
