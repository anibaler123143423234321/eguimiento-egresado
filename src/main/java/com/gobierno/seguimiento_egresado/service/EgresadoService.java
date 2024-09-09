package com.gobierno.seguimiento_egresado.service;

import com.gobierno.seguimiento_egresado.entity.Egresado;
import java.util.List;
import java.util.Optional;

public interface EgresadoService {
    List<Egresado> findAll();
    Optional<Egresado> findById(Long id);
    Egresado save(Egresado egresado);
    void deleteById(Long id);
}
