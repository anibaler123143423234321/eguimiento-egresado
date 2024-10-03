package com.gobierno.seguimiento_egresado.service;


import com.gobierno.seguimiento_egresado.entity.Role;
import com.gobierno.seguimiento_egresado.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface UserService {
    List<User> findAllUsers();
    User saveUser(User user);
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    void changeRole(Role newRole, String username);
    User findByUsernameReturnToken(String username);

    User findUserById(Long userId);

    Optional<User> getdByUsernameOrEmail(String nombreOrEmail);

    Optional<User> getByTokenPassword(String tokenPassword);

    User updateUser(Long id, User updateUser);
}
