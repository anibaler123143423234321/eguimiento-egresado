package com.gobierno.seguimiento_egresado.service;

import com.gobierno.seguimiento_egresado.entity.MovimientoEgresado;
import java.util.List;
import java.util.Optional;

public interface MovimientoEgresadoService {
    List<MovimientoEgresado> findAll();
    MovimientoEgresado save(MovimientoEgresado movimientoEgresado);
    List<MovimientoEgresado> getMovimientosByToken();
}
