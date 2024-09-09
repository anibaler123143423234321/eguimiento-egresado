package com.gobierno.seguimiento_egresado.controller;


import com.gobierno.seguimiento_egresado.entity.User;
import com.gobierno.seguimiento_egresado.service.AuthenticationService;
import com.gobierno.seguimiento_egresado.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://novedadesmonyclau.com")
@RequestMapping("api/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user)
    {
        if(userService.findByUsername(user.getUsername()).isPresent())
        {
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
        }

        if(userService.findByEmail(user.getEmail()).isPresent())
        {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user)
    {
        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
    }


}