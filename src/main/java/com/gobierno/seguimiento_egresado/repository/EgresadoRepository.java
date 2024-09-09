package com.gobierno.seguimiento_egresado.repository;

import com.gobierno.seguimiento_egresado.entity.Egresado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EgresadoRepository extends JpaRepository<Egresado, Long> {
}
