package com.plantasapi.plantas.controllers;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.Sensor;
import com.plantasapi.plantas.services.implement.SensorServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    SensorServiceImplement sensorService;

    @PatchMapping("")
    public ResponseEntity<Object> deleteFactory(@RequestBody Sensor editSensor){
        if(sensorService.ex)
        return new ResponseEntity<>(factoryService.updateFactory(newfactory),HttpStatus.ACCEPTED);
    }

}
