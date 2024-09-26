package com.gobierno.seguimiento_egresado.service;


import com.gobierno.seguimiento_egresado.entity.Egresado;
import com.gobierno.seguimiento_egresado.entity.User;

public interface AuthenticationService {

    User signInAndReturnJWT(User signInRequest);

    Egresado signInEgresadoAndReturnJWT(Egresado signInRequest);
}