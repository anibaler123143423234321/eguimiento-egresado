package com.gobierno.seguimiento_egresado.controller;

import com.gobierno.seguimiento_egresado.entity.Carrera;
import com.gobierno.seguimiento_egresado.service.CarreraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://novedadesmonyclau.com", "http://localhost:5200"})
@RequestMapping("api/carreras")
public class CarreraController {

    private final CarreraService carreraService;

    public CarreraController(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @GetMapping
    public List<Carrera> getAll() {
        return carreraService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getById(@PathVariable Long id) {
        return carreraService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Carrera create(@RequestBody Carrera carrera) {
        return carreraService.save(carrera);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrera> update(@PathVariable Long id, @RequestBody Carrera carrera) {
        return ResponseEntity.ok(carreraService.update(id, carrera));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carreraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
