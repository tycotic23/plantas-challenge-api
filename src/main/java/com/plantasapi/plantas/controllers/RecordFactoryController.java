package com.plantasapi.plantas.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecordFactoryController {

    @GetMapping("/probando")
    public ResponseEntity<Object> testing(){
    return new ResponseEntity<>("Funcionaaa", HttpStatus.ACCEPTED);
    }
}
