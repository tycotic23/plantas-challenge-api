package com.plantasapi.plantas.controllers;

import com.plantasapi.plantas.dtos.FactoryDTO;
import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.Usuario;
import com.plantasapi.plantas.services.implement.FactoryServiceImplement;
import com.plantasapi.plantas.services.implement.JWTService;
import com.plantasapi.plantas.services.implement.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/factory")
public class FactoryController {

    @Autowired
    private FactoryServiceImplement factoryService;

    @Autowired
    private UserServiceImplement userService;

    @GetMapping("")
    public ResponseEntity<Object> getAllFactories(Authentication authentication){
        //trae todas las plantas, pero solo del usuario autenticado
        String username=authentication.getName();
        return new ResponseEntity<>(factoryService.findAllUserFactoriesDTO(username), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFactory(Authentication authentication,@PathVariable Long id){
        //trae la planta en cuestion siempre que pertenezca al usuario autenticado
        String username=authentication.getName();
        return new ResponseEntity<>(factoryService.findFactoryUserDTO(username,id),HttpStatus.ACCEPTED);
    }


    @PostMapping("")
    public ResponseEntity<Object> createFactory(Authentication authentication,@RequestBody Factory newfactory){
        //guarda una planta para un determinado usuario
        newfactory.setUser((Usuario) authentication.getPrincipal());
        return new ResponseEntity<>(new FactoryDTO(factoryService.saveFactory(newfactory)),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFactory(Authentication authentication,@PathVariable Long id){
        //quede en este
        String username=authentication.getName();
        factoryService.deleteUserFactory(username,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> deleteFactory(@RequestBody Factory newfactory,@PathVariable Long id){
        return new ResponseEntity<>(factoryService.updateFactory(id,newfactory),HttpStatus.ACCEPTED);
    }

}
