package com.gobierno.seguimiento_egresado.controller;


import com.gobierno.seguimiento_egresado.entity.Role;
import com.gobierno.seguimiento_egresado.entity.User;
import com.gobierno.seguimiento_egresado.security.UserPrincipal;
import com.gobierno.seguimiento_egresado.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"https://novedadesmonyclau.com", "http://localhost:5200"})
@RequestMapping("api/user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("listar")
    public List<User> listUsers() {
        return userService.findAllUsers();
    }


    @PutMapping("change/{role}")
    public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Role role) {
        User user = userService.findByUsername(userPrincipal.getUsername()).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (role == Role.ADMIN && user.getRole() == Role.ADMIN) {
            // Actualiza el rol a SUPERADMIN si es un ADMIN
            userService.changeRole(Role.ADMIN, userPrincipal.getUsername());
            return ResponseEntity.ok(true);
        } else {
            // Maneja otros cambios de roles aquí si es necesario
            userService.changeRole(role, userPrincipal.getUsername());
            return ResponseEntity.ok(true);
        }
    }


    @GetMapping()
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal UserPrincipal userPrincipal)
    {
        return new ResponseEntity<>(userService.findByUsernameReturnToken(userPrincipal.getUsername()), HttpStatus.OK);
    }

    // http://locahost:5555/gateway/usuario/{userId}
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long userId) {
        try {
            User usuario = userService.findUserById(userId);
            if (usuario != null) {
                return ResponseEntity.ok(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
