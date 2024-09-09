package com.gobierno.seguimiento_egresado.repository;

import com.gobierno.seguimiento_egresado.entity.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarreraRepository extends JpaRepository<Carrera, Long> {
}
