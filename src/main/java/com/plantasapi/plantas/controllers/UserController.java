package com.plantasapi.plantas.controllers;

import com.plantasapi.plantas.models.Usuario;
import com.plantasapi.plantas.services.implement.AuthServiceImplement;
import com.plantasapi.plantas.services.implement.FactoryServiceImplement;
import com.plantasapi.plantas.services.implement.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private FactoryServiceImplement factoryService;

    @Autowired
    private AuthServiceImplement authService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody Usuario user){
        return new ResponseEntity<>(authService.register(user), HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Usuario user){
        return new ResponseEntity<>(authService.verify(user), HttpStatus.ACCEPTED);
    }

    /*@GetMapping("/{id}/factories")
    public ResponseEntity<Object> getAllUserFactories(@PathVariable Long id){
        return new ResponseEntity<>(factoryService.findAllUserFactories(id), HttpStatus.ACCEPTED);
    }*/
}
