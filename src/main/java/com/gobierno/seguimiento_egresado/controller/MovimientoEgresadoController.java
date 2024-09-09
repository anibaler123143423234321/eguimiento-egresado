package com.gobierno.seguimiento_egresado.controller;

import com.gobierno.seguimiento_egresado.entity.MovimientoEgresado;
import com.gobierno.seguimiento_egresado.service.MovimientoEgresadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoEgresado> getById(@PathVariable Long id) {
        return movimientoEgresadoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MovimientoEgresado create(@RequestBody MovimientoEgresado movimientoEgresado) {
        return movimientoEgresadoService.save(movimientoEgresado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoEgresado> update(@PathVariable Long id, @RequestBody MovimientoEgresado movimientoEgresado) {
        return ResponseEntity.ok(movimientoEgresadoService.update(id, movimientoEgresado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movimientoEgresadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
