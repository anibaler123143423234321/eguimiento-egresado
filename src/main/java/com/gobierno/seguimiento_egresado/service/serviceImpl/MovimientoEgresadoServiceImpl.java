package com.gobierno.seguimiento_egresado.service.serviceImpl;

import com.gobierno.seguimiento_egresado.entity.Egresado;
import com.gobierno.seguimiento_egresado.entity.MovimientoEgresado;
import com.gobierno.seguimiento_egresado.repository.EgresadoRepository;
import com.gobierno.seguimiento_egresado.repository.MovimientoEgresadoRepository;
import com.gobierno.seguimiento_egresado.service.MovimientoEgresadoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoEgresadoServiceImpl implements MovimientoEgresadoService {

    private final MovimientoEgresadoRepository movimientoEgresadoRepository;
    private final EgresadoRepository egresadoRepository;

    public MovimientoEgresadoServiceImpl(MovimientoEgresadoRepository movimientoEgresadoRepository, EgresadoRepository egresadoRepository) {
        this.movimientoEgresadoRepository = movimientoEgresadoRepository;
        this.egresadoRepository = egresadoRepository;
    }

    @Override
    public MovimientoEgresado save(MovimientoEgresado movimientoEgresado) {
        // Verificar si el Egresado existe
        Long egresadoId = movimientoEgresado.getEgresado().getId();
        if (egresadoId != null) {
            Optional<Egresado> optionalEgresado = egresadoRepository.findById(egresadoId);
            if (optionalEgresado.isPresent()) {
                // Si el Egresado existe, se lo asigna al MovimientoEgresado
                movimientoEgresado.setEgresado(optionalEgresado.get());
            } else {
                throw new RuntimeException("Egresado not found with id: " + egresadoId);
            }
        } else {
            throw new IllegalArgumentException("Egresado ID must not be null");
        }
        // Guardar el MovimientoEgresado
        return movimientoEgresadoRepository.save(movimientoEgresado);
    }

    @Override
    public List<MovimientoEgresado> findAll() {
        return movimientoEgresadoRepository.findAll();
    }

    @Override
    public List<MovimientoEgresado> getMovimientosByToken() {
        // Obtener el usuario autenticado del contexto de seguridad
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

        // Buscar el egresado por su nombre de usuario
        Optional<Egresado> optionalEgresado = egresadoRepository.findByUsername(username);
        if (optionalEgresado.isPresent()) {
            Egresado egresado = optionalEgresado.get();
            return movimientoEgresadoRepository.findByEgresadoId(egresado.getId());
        } else {
            throw new RuntimeException("Egresado no encontrado para el usuario: " + username);
        }
    }

}
