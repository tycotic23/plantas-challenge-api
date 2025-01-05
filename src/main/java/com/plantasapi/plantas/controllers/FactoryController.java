package com.plantasapi.plantas.controllers;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.services.implement.FactoryServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/factory")
public class FactoryController {

    @Autowired
    private FactoryServiceImplement factoryService;

    @GetMapping("")
    public ResponseEntity<Object> getAllFactories(){

        return new ResponseEntity<>(factoryService.findAllFactories(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFactory(@PathVariable Long id){
        return new ResponseEntity<>(factoryService.findFactory(id),HttpStatus.ACCEPTED);
    }

    @PostMapping("")
    public ResponseEntity<Object> createFactory(@RequestBody Factory newfactory){
        return new ResponseEntity<>(factoryService.saveFactory(newfactory),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFactory(@PathVariable Long id){
        factoryService.deleteFactory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> deleteFactory(@RequestBody Factory newfactory,@PathVariable Long id){
        return new ResponseEntity<>(factoryService.updateFactory(id,newfactory),HttpStatus.ACCEPTED);
    }

}
