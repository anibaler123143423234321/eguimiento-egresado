package com.gobierno.seguimiento_egresado.repository;

import com.gobierno.seguimiento_egresado.entity.Egresado;
import com.gobierno.seguimiento_egresado.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EgresadoRepository extends JpaRepository<Egresado, Long> {
    Optional<Egresado> findByEmail(String email);
    Optional<Egresado> findByUsername(String username);
    Optional<Egresado> findByUsernameOrEmail(String username, String email);

}
