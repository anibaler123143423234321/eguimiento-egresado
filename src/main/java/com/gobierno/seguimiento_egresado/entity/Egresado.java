package com.gobierno.seguimiento_egresado.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "egresado")
public class Egresado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "egre_id")
    private Long id;

    @Column(name = "egre_dni", nullable = false, length = 8, unique = true)
    private String dni;

    @Column(name = "egre_apellidos", nullable = false, length = 40)
    private String apellidos;

    @Column(name = "egre_nombre", nullable = false, length = 40)
    private String nombre;

    @Column(name = "egre_sexo", nullable = false, length = 1)
    private char sexo;

    @Column(name = "egre_fn", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "egre_carrera", nullable = false)
    private Long idCarrera;

    @Column(name = "egre_anioegreso", nullable = false)
    private Integer anioEgreso;

    @Column(name = "egre_telefono1", length = 20)
    private String telefono1;

    @Column(name = "egre_telefono2", length = 20)
    private String telefono2;

    @Column(name = "egre_titulado", nullable = false, length = 3)
    private String titulado; // Cambiado a String

    @Column(name = "egre_discapacidad", nullable = false, length = 3)
    private String discapacidad; // Cambiado a String

    @Column(name = "egre_estado", nullable = false, length = 1)
    private String estado = "A"; // Valor por defecto

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    // ... otros campos
    @Column(name = "usuario", nullable = false, length = 50, unique = true)
    private String username;
    @Column(name = "egre_email", nullable = false, unique = true)
    private String email;

    @Column(name = "egre_password", nullable = false)
    private String password;

    @Transient
    private String token;

    @ManyToOne
    @JoinColumn(name = "egre_carrera", insertable = false, updatable = false)
    @JsonIgnore // O @JsonManagedReference si hay una relación bidireccional
    private Carrera carrera;
}
