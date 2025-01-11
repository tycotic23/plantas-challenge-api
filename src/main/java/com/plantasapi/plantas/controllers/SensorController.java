package com.plantasapi.plantas.controllers;

import com.plantasapi.plantas.dtos.SensorCreateDTO;
import com.plantasapi.plantas.dtos.SensorDTO;
import com.plantasapi.plantas.models.*;
import com.plantasapi.plantas.services.implement.FactoryServiceImplement;
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
    private SensorServiceImplement sensorService;

    @Autowired
    private FactoryServiceImplement factoryService;

    @Autowired
    private RecordSensorServiceImplement recordSensorService;

    @Autowired
    TypeSensorServiceImplement typeSensorService;


    @PostMapping("")
    public ResponseEntity<Object> createSensor(Authentication authentication, @RequestBody SensorCreateDTO sensor){
        //guarda un sensor para una planta del usuario autenticado
        if(sensor.getType()==null){
            return new ResponseEntity<>("Falta el tipo",HttpStatus.NOT_FOUND);
        }
        if(sensor.getFactory_id()<0){
            return new ResponseEntity<>("Planta inexistente",HttpStatus.NOT_FOUND);
        }
        //revisa que la platan exista y pertenezca al usuario
        String username=authentication.getName();
        Sensor oldSensor=sensorService.findByFactory_user_usernameAndFactory_idAndType_type(username, sensor.getFactory_id(), sensor.getType());
        if (oldSensor==null) {
            //si no existia hay que crearlo
            //verificar datos
            if(!typeSensorService.existsByType(sensor.getType())){
                return new ResponseEntity<>("Tipo de sensor inexistente",HttpStatus.NOT_FOUND);
            }

            if(!factoryService.existsByUser_usernameAndId(username, sensor.getFactory_id())){
                return new ResponseEntity<>("Planta inexistente",HttpStatus.NOT_FOUND);
            }

            TypeSensor typeSensor=typeSensorService.findByType(sensor.getType());
            Factory factory=factoryService.findFactoryUser(username, sensor.getFactory_id());
            Sensor newSensor=new Sensor(sensor.getReadings(), sensor.getMedium_alerts(), sensor.getRed_alerts(),0);
            newSensor.setFactory(factory);
            newSensor.setType(typeSensor);

            //guardar en el historial
            RecordSensor recordSensor= new RecordSensor("Crear Sensor de tipo "+typeSensor.getType() + "en la planta "+factory.getName(), LocalDateTime.now());
            recordSensor.setUser((Usuario) authentication.getPrincipal());
            recordSensorService.saveRecordSensor(recordSensor);
            return new ResponseEntity<>(new SensorDTO(sensorService.saveSensor(newSensor)),HttpStatus.CREATED);
        }

        //sumando los nuevos valores a lo que ya habia
        if(sensor.getReadings()>=0){
            oldSensor.setReadings(oldSensor.getReadings()+sensor.getReadings());
        }
        if(sensor.getMedium_alerts()>=0){
            oldSensor.setMediumAlerts(oldSensor.getMediumAlerts()+sensor.getMedium_alerts());
        }
        if(sensor.getRed_alerts()>=0){
            oldSensor.setRedAlerts(oldSensor.getRedAlerts()+sensor.getRed_alerts());
        }



        //guardar en el historial
        RecordSensor recordSensor= new RecordSensor("Modificar Sensor de tipo "+oldSensor.getType().getType() + "en la planta "+oldSensor.getFactory().getName(), LocalDateTime.now());
        recordSensor.setUser((Usuario) authentication.getPrincipal());
        recordSensorService.saveRecordSensor(recordSensor);

        return new ResponseEntity<>(new SensorDTO(sensorService.updateSensor(oldSensor.getId(), oldSensor)),HttpStatus.ACCEPTED);
    }

    @PatchMapping("/disable")
    public ResponseEntity<Object> disableSensor(Authentication authentication,@RequestBody SensorCreateDTO sensorSearch) {
        String username=authentication.getName();
        Sensor sensor=sensorService.findByFactory_user_usernameAndFactory_idAndType_type(username, sensorSearch.getFactory_id(), sensorSearch.getType());
        if(sensor==null){
            return new ResponseEntity<>("Sensor no encontrado",HttpStatus.NOT_FOUND);
        }
        sensor.setDisabledSensors(1);
        //guardar en el historial
        RecordSensor recordSensor= new RecordSensor("Deshabilitar Sensor de tipo "+sensor.getType().getType() + "en la planta "+sensor.getFactory().getName(), LocalDateTime.now());
        recordSensor.setUser((Usuario) authentication.getPrincipal());
        recordSensorService.saveRecordSensor(recordSensor);

        return new ResponseEntity<>(new SensorDTO(sensorService.updateSensor(sensor.getId(), sensor)),HttpStatus.ACCEPTED);
    }

    @PatchMapping("/enable")
    public ResponseEntity<Object> enableSensor(Authentication authentication,@RequestBody SensorCreateDTO sensorSearch) {
        String username=authentication.getName();
        Sensor sensor=sensorService.findByFactory_user_usernameAndFactory_idAndType_type(username, sensorSearch.getFactory_id(), sensorSearch.getType());
        if(sensor==null){
            return new ResponseEntity<>("Sensor no encontrado",HttpStatus.NOT_FOUND);
        }
        sensor.setDisabledSensors(0);
        //guardar en el historial
        RecordSensor recordSensor= new RecordSensor("Habilitar Sensor de tipo "+sensor.getType().getType() + "en la planta "+sensor.getFactory().getName(), LocalDateTime.now());
        recordSensor.setUser((Usuario) authentication.getPrincipal());
        recordSensorService.saveRecordSensor(recordSensor);

        return new ResponseEntity<>(new SensorDTO(sensorService.updateSensor(sensor.getId(), sensor)),HttpStatus.ACCEPTED);
    }

}
