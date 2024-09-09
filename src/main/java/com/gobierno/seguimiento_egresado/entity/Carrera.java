package com.gobierno.seguimiento_egresado.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "carrera")
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcarrera")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
}
