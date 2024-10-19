package com.gobierno.seguimiento_egresado.controller;

import com.gobierno.seguimiento_egresado.entity.MovimientoEgresado;
import com.gobierno.seguimiento_egresado.service.MovimientoEgresadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = { "http://localhost:5200", "https://seguimiento-egresado.web.app"})
@RequestMapping("api/movimientos-egresados")
public class MovimientoEgresadoController {

    private final MovimientoEgresadoService movimientoEgresadoService;

    public MovimientoEgresadoController(MovimientoEgresadoService movimientoEgresadoService) {
        this.movimientoEgresadoService = movimientoEgresadoService;
    }

    @GetMapping
    public List<MovimientoEgresado> getAll() {
        return movimientoEgresadoService.findAll();
    }

    @GetMapping("movimientos")
    public ResponseEntity<List<MovimientoEgresado>> getMovimientosByToken() {
        List<MovimientoEgresado> movimientos = movimientoEgresadoService.getMovimientosByToken();
        return ResponseEntity.ok(movimientos);
    }

    @PostMapping
    public MovimientoEgresado create(@RequestBody MovimientoEgresado movimientoEgresado) {
        return movimientoEgresadoService.save(movimientoEgresado);
    }

}
