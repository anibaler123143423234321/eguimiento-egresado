package com.gobierno.seguimiento_egresado.security;


import com.gobierno.seguimiento_egresado.entity.User;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("El usuario no fue encontrado:"+username));


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
