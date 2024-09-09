package com.gobierno.seguimiento_egresado.service.serviceImpl;

import com.gobierno.seguimiento_egresado.entity.Egresado;
import com.gobierno.seguimiento_egresado.entity.Role;
import com.gobierno.seguimiento_egresado.repository.EgresadoRepository;
import com.gobierno.seguimiento_egresado.service.EgresadoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EgresadoServiceImpl implements EgresadoService {

    private final EgresadoRepository egresadoRepository;

    public EgresadoServiceImpl(EgresadoRepository egresadoRepository) {
        this.egresadoRepository = egresadoRepository;
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
    public Egresado save(Egresado egresado) {
        // Asegurarse de que el role se establezca en EGRESADO por defecto
        if (egresado.getRole() == null) {
            egresado.setRole(Role.EGRESADO);
        }
        return egresadoRepository.save(egresado);
    }


    @Override
    public void deleteById(Long id) {
        egresadoRepository.deleteById(id);
    }
}
