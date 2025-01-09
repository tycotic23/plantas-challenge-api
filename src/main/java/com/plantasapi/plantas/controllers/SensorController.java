package com.plantasapi.plantas.controllers;

import com.plantasapi.plantas.dtos.SensorCreateDTO;
import com.plantasapi.plantas.dtos.SensorDTO;
import com.plantasapi.plantas.models.*;
import com.plantasapi.plantas.services.implement.RecordSensorServiceImplement;
import com.plantasapi.plantas.services.implement.SensorServiceImplement;
import com.plantasapi.plantas.services.implement.TypeSensorServiceImplement;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    SensorServiceImplement sensorService;

    @Autowired
    private RecordSensorServiceImplement recordSensorService;

    @Autowired
    TypeSensorServiceImplement typeSensorService;


    @PatchMapping("/{id}")
    public ResponseEntity<Object> createSensor(Authentication authentication, @RequestBody SensorCreateDTO sensor, @PathVariable Long id){
        //guarda un sensor para una planta del usuario autenticado
        //revisa que la platan exista y pertenezca al usuario
        String username=authentication.getName();
        Sensor oldSensor=sensorService.findByFactory_user_usernameAndId(username,id);
        if (oldSensor==null) {
            return new ResponseEntity<>("Sensor no encontrado",HttpStatus.NOT_FOUND);
        }

        //validacion de los campos
        if(sensor.getReadings()>=0){
            oldSensor.setReadings(sensor.getReadings());
        }
        if(sensor.getDisabled_sensors()>=0){
            oldSensor.setDisabledSensors(sensor.getDisabled_sensors());
        }
        if(sensor.getMedium_alerts()>=0){
            oldSensor.setMediumAlerts(sensor.getMedium_alerts());
        }
        if(sensor.getRed_alerts()>=0){
            oldSensor.setRedAlerts(sensor.getRed_alerts());
        }

        if(sensor.getType()!=null){
            TypeSensor type = typeSensorService.findByType(sensor.getType());
            if(type!=null){
                oldSensor.setType(type);
            }
        }

        //guardar en el historial
        RecordSensor recordSensor= new RecordSensor("Crear Sensor de tipo "+oldSensor.getType().getType() + "en la planta "+oldSensor.getFactory().getName(), LocalDateTime.now());
        recordSensor.setUser((Usuario) authentication.getPrincipal());
        recordSensorService.saveRecordSensor(recordSensor);

        return new ResponseEntity<>(new SensorDTO(sensorService.updateSensor(id,oldSensor)),HttpStatus.ACCEPTED);
    }

}
