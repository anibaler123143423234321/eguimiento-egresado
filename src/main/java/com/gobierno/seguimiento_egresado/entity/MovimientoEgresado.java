package com.gobierno.seguimiento_egresado.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "movimiento_egresado")
public class MovimientoEgresado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mov_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "egre_id", nullable = false)
    private Egresado egresado;

    @Column(name = "empresa", nullable = false, length = 100)
    private String empresa;

    @Column(name = "cargo", nullable = false, length = 50)
    private String cargo;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "observaciones", length = 255)
    private String observaciones;
}
