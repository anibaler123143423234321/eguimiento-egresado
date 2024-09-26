package com.gobierno.seguimiento_egresado.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codi_usuario")
    private Long id;

    @Column(name = "usuario", nullable = false, length = 50, unique = true)
    private String username;

    @Column(name = "clave", nullable = false, length = 100)
    private String password;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="apellido", nullable = false)
    private String apellido;

    @Column(name="telefono", nullable = false)
    private String telefono;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_cese")
    private LocalDate fechaCese;

    @Column(name = "estado", nullable = false, length = 1)
    private String estado = "A"; // Valor por defecto

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Transient
    private String token;

}
