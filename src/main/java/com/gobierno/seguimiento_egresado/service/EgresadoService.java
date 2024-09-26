package com.gobierno.seguimiento_egresado.service;

import com.gobierno.seguimiento_egresado.entity.Egresado;

import java.util.List;
import java.util.Optional;

public interface EgresadoService {
    List<Egresado> findAll();
    Optional<Egresado> findById(Long id);
    Egresado saveEgresado(Egresado egresado);

    Optional<Egresado> findByEmail(String email);

    Optional<Egresado> findByUsername(String username);

    void deleteById(Long id);
}
