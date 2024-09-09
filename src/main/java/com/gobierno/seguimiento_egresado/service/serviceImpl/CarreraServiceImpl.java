package com.gobierno.seguimiento_egresado.service.serviceImpl;

import com.gobierno.seguimiento_egresado.entity.Carrera;
import com.gobierno.seguimiento_egresado.repository.CarreraRepository;
import com.gobierno.seguimiento_egresado.service.CarreraService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraServiceImpl implements CarreraService {

    private final CarreraRepository carreraRepository;

    public CarreraServiceImpl(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    @Override
    public List<Carrera> findAll() {
        return carreraRepository.findAll();
    }

    @Override
    public Optional<Carrera> findById(Long id) {
        return carreraRepository.findById(id);
    }

    @Override
    public Carrera save(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    @Override
    public Carrera update(Long id, Carrera carrera) {
        return carreraRepository.findById(id).map(existingCarrera -> {
            existingCarrera.setNombre(carrera.getNombre());
            return carreraRepository.save(existingCarrera);
        }).orElseThrow(() -> new RuntimeException("Carrera not found"));
    }

    @Override
    public void deleteById(Long id) {
        carreraRepository.deleteById(id);
    }
}
