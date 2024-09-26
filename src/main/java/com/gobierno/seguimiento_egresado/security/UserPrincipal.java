package com.gobierno.seguimiento_egresado.security;

import com.gobierno.seguimiento_egresado.entity.Egresado;
import com.gobierno.seguimiento_egresado.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPrincipal implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private User user;           // Solo se usa al iniciar sesión como usuario
    private Egresado egresado;  // Solo se usa al iniciar sesión como egresado
    private Set<GrantedAuthority> authorities; // Se mantiene si es necesario

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Constructor que toma un User como parámetro
    public UserPrincipal(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.user = user;
    }

    // Constructor que toma un Egresado como parámetro
    public UserPrincipal(Egresado egresado) {
        this.id = egresado.getId(); // Asegúrate de que Egresado tenga un método getId()
        this.username = egresado.getEmail(); // O el campo que uses para el nombre de usuario
        this.password = egresado.getPassword();
        this.egresado = egresado;
    }

    public static UserPrincipal build(User user) {
        return new UserPrincipal(user);
    }

    public static UserPrincipal build(Egresado egresado) {
        return new UserPrincipal(egresado);
    }
}