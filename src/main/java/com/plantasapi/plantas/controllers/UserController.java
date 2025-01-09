package com.plantasapi.plantas.controllers;

import com.plantasapi.plantas.dtos.SensorDTO;
import com.plantasapi.plantas.models.Usuario;
import com.plantasapi.plantas.services.SensorService;
import com.plantasapi.plantas.services.implement.AuthServiceImplement;
import com.plantasapi.plantas.services.implement.FactoryServiceImplement;
import com.plantasapi.plantas.services.implement.SensorServiceImplement;
import com.plantasapi.plantas.services.implement.UserServiceImplement;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private FactoryServiceImplement factoryService;

    @Autowired
    private AuthServiceImplement authService;

    @Autowired
    private SensorServiceImplement sensorService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody Usuario user){
        return new ResponseEntity<>(authService.register(user), HttpStatus.ACCEPTED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Usuario user){
        return new ResponseEntity<>(authService.verify(user), HttpStatus.ACCEPTED);
    }

    @GetMapping("/checkLogin")
    public ResponseEntity<Object> checkLogin(){
        //revisa si el token sigue siendo válido, útil para revisar un token que quedó en una cookie
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sensor")
    public ResponseEntity<Object> groupByType(Authorization authorization){
        return new ResponseEntity<>(sensorService.groupByType(),HttpStatus.OK);
    }
}
