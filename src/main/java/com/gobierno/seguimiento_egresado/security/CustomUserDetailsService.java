package com.gobierno.seguimiento_egresado.security;


import com.gobierno.seguimiento_egresado.entity.Egresado;
import com.gobierno.seguimiento_egresado.entity.User;
import com.gobierno.seguimiento_egresado.service.EgresadoService;
import com.gobierno.seguimiento_egresado.service.UserService;
import com.gobierno.seguimiento_egresado.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private EgresadoService egresadoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Intentar cargar como un User
        User user = userService.findByUsername(username)
                .orElse(null);

        if (user == null) {
            // Intentar cargar como un Egresado
            Egresado egresado = egresadoService.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("El usuario o egresado no fue encontrado: " + username));

            Set<GrantedAuthority> authorities = Collections.singleton(SecurityUtils.convertToAuthority(egresado.getRole().name()));

            return UserPrincipal.builder()
                    .egresado(egresado)
                    .id(egresado.getId())
                    .username(egresado.getUsername()) // Aquí puedes usar el email como username
                    .password(egresado.getPassword())
                    .authorities(authorities)
                    .build();
        }

        // Si es un User, devuelves el UserPrincipal correspondiente
        Set<GrantedAuthority> authorities = Collections.singleton(SecurityUtils.convertToAuthority(user.getRole().name()));

        return UserPrincipal.builder()
                .user(user)
                .id(user.getId())
                .username(username)
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
