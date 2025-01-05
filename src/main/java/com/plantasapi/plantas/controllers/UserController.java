package com.plantasapi.plantas.controllers;

import com.plantasapi.plantas.services.implement.FactoryServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private FactoryServiceImplement factoryService;

    @GetMapping("/{id}/factories")
    public ResponseEntity<Object> getAllUserFactories(@PathVariable Long id){
        return new ResponseEntity<>(factoryService.findAllUserFactories(id), HttpStatus.ACCEPTED);
    }
}
