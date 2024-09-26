package com.gobierno.seguimiento_egresado.service.serviceImpl;

import com.gobierno.seguimiento_egresado.entity.Egresado;
import com.gobierno.seguimiento_egresado.entity.Role;
import com.gobierno.seguimiento_egresado.repository.EgresadoRepository;
import com.gobierno.seguimiento_egresado.service.EgresadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EgresadoServiceImpl implements EgresadoService {

    private final EgresadoRepository egresadoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public EgresadoServiceImpl(EgresadoRepository egresadoRepository, PasswordEncoder passwordEncoder) {
        this.egresadoRepository = egresadoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Egresado> findAll() {
        return egresadoRepository.findAll();
    }

    @Override
    public Optional<Egresado> findById(Long id) {
        return egresadoRepository.findById(id);
    }

    @Override
    public Egresado saveEgresado(Egresado egresado) {
        // Hashear la contraseña del egresado usando PasswordEncoder
        String encodedPassword = passwordEncoder.encode(egresado.getPassword());

        // Asignar el rol por defecto si no está definido
        if (egresado.getRole() == null) {
            egresado.setRole(Role.EGRESADO);
        }

        // Establecer la contraseña hasheada en el egresado
        egresado.setPassword(encodedPassword);

        // Guardar el egresado en el repositorio
        return egresadoRepository.save(egresado);
    }


    @Override
    public Optional<Egresado> findByEmail(String email) {
        return egresadoRepository.findByEmail(email);
    }

    @Override
    public Optional<Egresado> findByUsername(String username)
    {
        return egresadoRepository.findByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        egresadoRepository.deleteById(id);
    }
}
