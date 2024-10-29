package com.gobierno.seguimiento_egresado.service.serviceImpl;

import com.gobierno.seguimiento_egresado.entity.Egresado;
import com.gobierno.seguimiento_egresado.entity.PageableQuery;
import com.gobierno.seguimiento_egresado.entity.Role;
import com.gobierno.seguimiento_egresado.repository.EgresadoRepository;
import com.gobierno.seguimiento_egresado.security.jwt.JwtProvider;
import com.gobierno.seguimiento_egresado.service.EgresadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.Optional;

@Service
public class EgresadoServiceImpl implements EgresadoService {

    private final EgresadoRepository egresadoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    public EgresadoServiceImpl(EgresadoRepository egresadoRepository, PasswordEncoder passwordEncoder) {
        this.egresadoRepository = egresadoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*
    @Override
    public List<Egresado> findAll() {
        return egresadoRepository.findAll();
    }
*/
    @Override
    public Page<Egresado> findAll(PageableQuery pageableQuery) {
        Sort sort = Sort.by(Sort.Direction.fromString(pageableQuery.getEnOrden()),
                pageableQuery.getOrdenadorPor());
        Pageable pageable = PageRequest.of(pageableQuery.getPagina(),
                pageableQuery.getElementosPorPagina(), sort);
        return egresadoRepository.findAll(pageable);
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
    public Egresado findByUsernameEgresadoReturnToken(String username) {
        // Buscamos al usuario en la base de datos utilizando el repository
        Egresado egresado = egresadoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El egresado no existe: " + username));

        // Mensaje en consola para indicar que el usuario fue encontrado
        System.out.println("Usuario encontrado: " + egresado.getUsername() + " - " + egresado.getEmail());

        // Generamos el token JWT
        String jwt = jwtProvider.generateTokenForEgresado(egresado);

        // Asignamos el token generado al usuario
        egresado.setToken(jwt);

        // Devolvemos el objeto usuario con el token
        return egresado;
    }

    @Override
    public void deleteById(Long id) {
        egresadoRepository.deleteById(id);
    }
}
