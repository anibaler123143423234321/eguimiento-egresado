package com.gobierno.seguimiento_egresado.controller;

import com.gobierno.seguimiento_egresado.entity.Egresado;
import com.gobierno.seguimiento_egresado.service.EgresadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = { "http://localhost:5200", "https://seguimiento-egresado.web.app"})
@RequestMapping("api/egresados")
public class EgresadoController {

    private final EgresadoService egresadoService;

    public EgresadoController(EgresadoService egresadoService) {
        this.egresadoService = egresadoService;
    }

    @GetMapping
    public List<Egresado> getAll() {
        return egresadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Egresado> getById(@PathVariable Long id) {
        return egresadoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Egresado create(@RequestBody Egresado egresado) {
        return egresadoService.saveEgresado(egresado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        egresadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
