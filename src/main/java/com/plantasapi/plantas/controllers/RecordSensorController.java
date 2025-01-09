package com.plantasapi.plantas.controllers;

import com.plantasapi.plantas.services.implement.RecordFactoryServiceImplement;
import com.plantasapi.plantas.services.implement.RecordSensorServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recordSensor")
public class RecordSensorController {
    @Autowired
    private RecordSensorServiceImplement recordSensorService;
    @GetMapping("")
    public ResponseEntity<Object> getAllRecordFactories(Authentication authentication){
        //trae el historial de los sensores del usuario autenticado
        String username=authentication.getName();
        return new ResponseEntity<>(recordSensorService.findByUser_usernameDTO(username), HttpStatus.ACCEPTED);
    }

}
