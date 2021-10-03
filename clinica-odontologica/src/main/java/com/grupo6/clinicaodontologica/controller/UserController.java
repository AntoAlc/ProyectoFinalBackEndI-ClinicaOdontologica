package com.grupo6.clinicaodontologica.controller;


import com.grupo6.clinicaodontologica.persistence.model.authentication.User;
import com.grupo6.clinicaodontologica.service.Impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    final static Logger log = Logger.getLogger(OdontologoController.class);

    @Autowired
    UserServiceImpl userService;


    @GetMapping("/")
    public String home(){
        return "<h1> Bienvenido a la clinica odontológica de DH</h1>";
    }


    @PostMapping("/users/crear")
    public ResponseEntity<User> crear(@RequestBody User user){

        log.debug("Se está ejecutando el método 'crear' en User");

       // BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
        //String encodedPass = passwordEncoder.encode(user.getPassword());
        //user.setPassword(encodedPass);
        return ResponseEntity.ok(userService.crear(user));
    }

    @GetMapping("users/buscarTodos")
    public ResponseEntity<List<User>> buscarTodos() {
        log.debug("---- Buscando todos los usuarios----");
        return (ResponseEntity<List<User>>) ResponseEntity.ok(userService.buscarTodos());
    }


}
