package com.plantasapi.plantas.controllers;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.Sensor;
import com.plantasapi.plantas.services.implement.SensorServiceImplement;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    SensorServiceImplement sensorService;

    @DeleteMapping("")
    public ResponseEntity<Object> deleteFactory(@RequestParam long factory_id, @RequestParam long type_id){
        if(!sensorService.existsByFactory_idAndByType_id(factory_id,type_id)){
            return new ResponseEntity<>("No se encuentra el sensor",HttpStatus.NOT_FOUND);
        }
        sensorService.deleteByFactory_idAndType_id(factory_id,type_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
