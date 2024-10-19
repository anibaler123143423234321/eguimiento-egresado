package com.gobierno.seguimiento_egresado.security.jwt;

import com.gobierno.seguimiento_egresado.entity.Egresado;
import com.gobierno.seguimiento_egresado.entity.User;
import com.gobierno.seguimiento_egresado.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JwtProvider {

    String generateToken(UserPrincipal auth);

    Authentication getAuthentication(HttpServletRequest request);

    String generateTokenForEgresado(Egresado egresado);

    String generateToken(User user);

    boolean isTokenValid(HttpServletRequest request);
}