package com.gobierno.seguimiento_egresado.service.serviceImpl;


import com.gobierno.seguimiento_egresado.entity.Egresado;
import com.gobierno.seguimiento_egresado.entity.User;
import com.gobierno.seguimiento_egresado.repository.EgresadoRepository;
import com.gobierno.seguimiento_egresado.repository.UserRepository;
import com.gobierno.seguimiento_egresado.security.UserPrincipal;
import com.gobierno.seguimiento_egresado.security.jwt.JwtProvider;
import com.gobierno.seguimiento_egresado.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EgresadoRepository egresadoRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User signInAndReturnJWT(User signInRequest) {
        User user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no fue encontrado:" + signInRequest.getEmail()));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), signInRequest.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userPrincipal);

        User signedInUser = userPrincipal.getUser();
        signedInUser.setToken(jwt);

        return signedInUser;
    }

    @Override
    public Egresado signInEgresadoAndReturnJWT(Egresado signInRequest) {
        Egresado egresado = egresadoRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("El egresado no fue encontrado: " + signInRequest.getEmail()));

        // Verificar si la contraseña ingresada coincide con la encriptada
        boolean passwordMatches = passwordEncoder.matches(signInRequest.getPassword(), egresado.getPassword());

        if (!passwordMatches) {
            throw new RuntimeException("La contraseña no coincide");
        }
        System.out.println("Email recibido: " + signInRequest.getEmail());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(egresado.getEmail(), signInRequest.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtProvider.generateTokenForEgresado(userPrincipal);

        egresado.setToken(jwt); // Asigna el token al egresado

        return egresado;
    }

}
