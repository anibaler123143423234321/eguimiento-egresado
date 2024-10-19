package com.gobierno.seguimiento_egresado.controller;

import com.gobierno.seguimiento_egresado.entity.Egresado;
import com.gobierno.seguimiento_egresado.entity.User;
import com.gobierno.seguimiento_egresado.security.UserPrincipal;
import com.gobierno.seguimiento_egresado.service.EgresadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/all")
    public List<Egresado> getAll() {
        return egresadoService.findAll();
    }

    @GetMapping
    public ResponseEntity<Egresado> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Egresado egresado = egresadoService.findByUsernameEgresadoReturnToken(userPrincipal.getUsername());
        return new ResponseEntity<>(egresado, HttpStatus.OK);
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
