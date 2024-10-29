package com.gobierno.seguimiento_egresado.service;

import com.gobierno.seguimiento_egresado.entity.Egresado;
import com.gobierno.seguimiento_egresado.entity.PageableQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EgresadoService {
    //List<Egresado> findAll();

    Page<Egresado> findAll(PageableQuery pageableQuery);

    Optional<Egresado> findById(Long id);
    Egresado saveEgresado(Egresado egresado);

    Optional<Egresado> findByEmail(String email);

    Optional<Egresado> findByUsername(String username);
    Egresado findByUsernameEgresadoReturnToken(String username);

    void deleteById(Long id);
}
