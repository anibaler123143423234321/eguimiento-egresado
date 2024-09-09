package com.gobierno.seguimiento_egresado.service;

import com.gobierno.seguimiento_egresado.entity.MovimientoEgresado;
import java.util.List;
import java.util.Optional;

public interface MovimientoEgresadoService {
    List<MovimientoEgresado> findAll();
    Optional<MovimientoEgresado> findById(Long id);
    MovimientoEgresado save(MovimientoEgresado movimientoEgresado);
    MovimientoEgresado update(Long id, MovimientoEgresado movimientoEgresado);
    void deleteById(Long id);
}
