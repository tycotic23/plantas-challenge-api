package com.plantasapi.plantas.controllers;

import com.plantasapi.plantas.services.implement.RecordFactoryServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recordFactory")
public class RecordFactoryController {

    @Autowired
    private RecordFactoryServiceImplement recordFactoryService;
    @GetMapping("")
    public ResponseEntity<Object> getAllRecordFactories(Authentication authentication){
        //trae el historial de las plantas del usuario autenticado
        String username=authentication.getName();
        return new ResponseEntity<>(recordFactoryService.findByUser_usernameDTO(username), HttpStatus.ACCEPTED);
    }
}
