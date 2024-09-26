package com.gobierno.seguimiento_egresado.repository;

import com.gobierno.seguimiento_egresado.entity.Egresado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EgresadoRepository extends JpaRepository<Egresado, Long> {
    Optional<Egresado> findByEmail(String email);
    Optional<Egresado> findByUsername(String username);

}
