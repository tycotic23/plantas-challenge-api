package com.plantasapi.plantas.controllers;


import com.plantasapi.plantas.dtos.SensorDTO;
import com.plantasapi.plantas.dtos.UserLoginResponseDTO;
import com.plantasapi.plantas.models.Usuario;
import com.plantasapi.plantas.services.implement.AuthServiceImplement;
import com.plantasapi.plantas.services.implement.FactoryServiceImplement;
import com.plantasapi.plantas.services.implement.SensorServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        UserLoginResponseDTO userLogin=authService.verify(user);
        if(userLogin.getToken().isEmpty()){
            return new ResponseEntity<>("Usuario o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userLogin, HttpStatus.ACCEPTED);
    }

    @GetMapping("/checkLogin")
    public ResponseEntity<Object> checkLogin(){
        //revisa si el token sigue siendo válido, útil para revisar un token que quedó en una cookie
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /*
    * Trae todos los sensores de todas las plantas del usuario autenticado agrupadas y sumadas por tipo en una unica coleccion
    *
    * */
    @GetMapping("/sensor")
    public ResponseEntity<Map<String, SensorDTO>> groupByType(Authentication authentication){
        String username=authentication.getName();
        return new ResponseEntity<>(sensorService.groupByType(username),HttpStatus.OK);
    }
}
