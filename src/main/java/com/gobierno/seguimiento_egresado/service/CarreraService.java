package com.gobierno.seguimiento_egresado.service;

import com.gobierno.seguimiento_egresado.entity.Carrera;
import java.util.List;
import java.util.Optional;

public interface CarreraService {
    List<Carrera> findAll();
    Optional<Carrera> findById(Long id);
    Carrera save(Carrera carrera);
    Carrera update(Long id, Carrera carrera);
    void deleteById(Long id);
}
