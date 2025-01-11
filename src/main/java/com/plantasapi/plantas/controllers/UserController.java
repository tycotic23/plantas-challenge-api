package com.plantasapi.plantas.controllers;


import com.plantasapi.plantas.dtos.SensorDTO;
import com.plantasapi.plantas.dtos.UserLoginResponseDTO;
import com.plantasapi.plantas.models.Usuario;
import com.plantasapi.plantas.services.implement.AuthServiceImplement;
import com.plantasapi.plantas.services.implement.FactoryServiceImplement;
import com.plantasapi.plantas.services.implement.SensorServiceImplement;
import com.plantasapi.plantas.services.implement.UserServiceImplement;
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

    @Autowired
    private UserServiceImplement userService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Usuario user){

        //validaciones
        if(user.getUsername()==null || user.getUsername().isBlank()){
            return new ResponseEntity<>("Falta el campo username",HttpStatus.BAD_REQUEST);
        }
        if(user.getEmail()==null || user.getEmail().isBlank()){
            return new ResponseEntity<>("Falta el campo email "+user.getEmail(),HttpStatus.BAD_REQUEST);
        }
        if(user.getPassword()==null || user.getPassword().isBlank()){
            return new ResponseEntity<>("Falta el campo password",HttpStatus.BAD_REQUEST);
        }

        if(!user.getPassword().matches("(?=.*[0-9])(?=.*[@#$%^&+=]).{8,}")){
            return new ResponseEntity<>("Contraseña inválida. Debe tener más de 8 caracteres, contener al menos uno especial y un número",HttpStatus.BAD_REQUEST);
        }

        //el email debe ser unico
        if(userService.existsByEmail(user.getEmail())){
            return new ResponseEntity<>("Ya existe un usuario registrado con ese email",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //el usuario debe ser unico
        if(userService.existsByUsername(user.getUsername())){
            return new ResponseEntity<>("Ya existe ese nombre de usuario",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //crear usuario
        UserLoginResponseDTO registeredUser=authService.registerAndLogin(user);
        if(registeredUser.getToken().isEmpty()){
            return new ResponseEntity<>("Error al crear el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(registeredUser, HttpStatus.ACCEPTED);
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
    * Trae todos los sensores de todas las plantas del usuario autenticado reducidas en un solo sensor
    *
    * */
    @GetMapping("/sensorFlat")
    public ResponseEntity<SensorDTO> allSensorFlat(Authentication authentication){
        String username=authentication.getName();
        return new ResponseEntity<>(sensorService.reduceAll(username),HttpStatus.OK);
    }

    /*
     * Trae todos los sensores de todas las plantas del usuario autenticado agrupadas y sumadas por tipo en una unica coleccion
     *
     * */

    @GetMapping("/sensor")
    public ResponseEntity<Map<String, SensorDTO>> allSensorGroupByType(Authentication authentication){
        String username=authentication.getName();
        return new ResponseEntity<>(sensorService.groupByType(username),HttpStatus.OK);
    }
}
