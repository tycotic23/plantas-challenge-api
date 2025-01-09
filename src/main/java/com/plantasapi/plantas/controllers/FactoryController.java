package com.plantasapi.plantas.controllers;

import com.plantasapi.plantas.dtos.FactoryDTO;
import com.plantasapi.plantas.dtos.SensorCreateDTO;
import com.plantasapi.plantas.dtos.SensorDTO;
import com.plantasapi.plantas.models.*;
import com.plantasapi.plantas.services.implement.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
@RequestMapping("/factory")
public class FactoryController {

    @Autowired
    private FactoryServiceImplement factoryService;

    @Autowired
    private UserServiceImplement userService;

    @Autowired
    private TypeSensorServiceImplement typeSensorService;

    @Autowired
    private SensorServiceImplement sensorService;

    @Autowired
    private RecordFactoryServiceImplement recordFactoryService;

    @Autowired
    private RecordSensorServiceImplement recordSensorService;

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
        if(newfactory.getName()==null){
            return new ResponseEntity<>("Falta el campo name",HttpStatus.BAD_REQUEST);
        }

        if(newfactory.getCountry()==null){
            return new ResponseEntity<>("Falta el campo country",HttpStatus.BAD_REQUEST);
        }

        newfactory.setUser((Usuario) authentication.getPrincipal());
        Factory savedFactory=factoryService.saveFactory(newfactory);

        //guardar en el historial
        RecordFactory recordFactory= new RecordFactory("Crear Planta "+newfactory.getName(), LocalDateTime.now());
        recordFactory.setUser((Usuario) authentication.getPrincipal());
        recordFactoryService.saveRecordFactory(recordFactory);

        return new ResponseEntity<>(new FactoryDTO(savedFactory),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFactory(Authentication authentication,@PathVariable Long id){
        String username=authentication.getName();
        if (!factoryService.existsByUser_usernameAndId(username,id)) {
            return new ResponseEntity<>("Planta no encontrada",HttpStatus.NOT_FOUND);
        }
        Factory oldFactory=factoryService.findFactory(id);
        factoryService.deleteFactory(id);

        //guardar en el historial
        RecordFactory recordFactory= new RecordFactory("Borrar Planta "+oldFactory.getName(), LocalDateTime.now());
        recordFactory.setUser((Usuario) authentication.getPrincipal());
        recordFactoryService.saveRecordFactory(recordFactory);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> deleteFactory(Authentication authentication,@RequestBody Factory newfactory,@PathVariable Long id){
        String username=authentication.getName();
        Factory oldFactory=factoryService.findFactoryUser(username,id);
        if (oldFactory==null) {
            return new ResponseEntity<>("Planta no encontrada",HttpStatus.NOT_FOUND);
        }

        if(newfactory.getName()!=null){
            oldFactory.setName(newfactory.getName());
        }

        if(newfactory.getCountry()!=null){
            oldFactory.setCountry(newfactory.getCountry());
        }

        //guardar en el historial
        RecordFactory recordFactory= new RecordFactory("Modificar Planta "+newfactory.getName(), LocalDateTime.now());
        recordFactory.setUser((Usuario) authentication.getPrincipal());
        recordFactoryService.saveRecordFactory(recordFactory);

        return new ResponseEntity<>(new FactoryDTO(factoryService.updateFactory(id,newfactory)),HttpStatus.ACCEPTED);
    }



    @PostMapping("/{id}/sensor")
    public ResponseEntity<Object> createSensor(Authentication authentication, @RequestBody SensorCreateDTO sensor, @PathVariable Long id){
        //guarda un sensor para una planta del usuario autenticado
        //revisa que la platan exista y pertenezca al usuario
        String username=authentication.getName();
        Factory factory=factoryService.findFactoryUser(username,id);
        if (factory==null) {
            return new ResponseEntity<>("Planta no encontrada",HttpStatus.NOT_FOUND);
        }

        //validacion de los campos
        if(sensor.getReadings()<0){
            return new ResponseEntity<>("Falta el campo readings y debe ser mayor o igual a cero",HttpStatus.BAD_REQUEST);
        }
        if(sensor.getDisabled_sensors()<0){
            return new ResponseEntity<>("Falta el campo disabled_sensors y debe ser mayor o igual a cero",HttpStatus.BAD_REQUEST);
        }
        if(sensor.getMedium_alerts()<0){
            return new ResponseEntity<>("Falta el campo medium_alerts y debe ser mayor o igual a cero",HttpStatus.BAD_REQUEST);
        }
        if(sensor.getRed_alerts()<0){
            return new ResponseEntity<>("Falta el campo red_alerts y debe ser mayor o igual a cero",HttpStatus.BAD_REQUEST);
        }

        if(sensor.getType()==null){
            return new ResponseEntity<>("Falta el campo type",HttpStatus.BAD_REQUEST);
        }

        TypeSensor type = typeSensorService.findByType(sensor.getType());

        if(type==null){
            return new ResponseEntity<>("Campo type inválido",HttpStatus.BAD_REQUEST);
        }

        Sensor newSensor=new Sensor();
        newSensor.setFactory(factory);
        newSensor.setDisabledSensors(sensor.getDisabled_sensors());
        newSensor.setReadings(sensor.getReadings());
        newSensor.setRedAlerts(sensor.getRed_alerts());
        newSensor.setMediumAlerts(sensor.getMedium_alerts());
        newSensor.setType(type);

        //guardar en el historial
        RecordSensor recordSensor= new RecordSensor("Crear Sensor "+newSensor.getType().getType()+"en la planta "+newSensor.getFactory().getName(), LocalDateTime.now());
        recordSensor.setUser((Usuario) authentication.getPrincipal());
        recordSensorService.saveRecordSensor(recordSensor);

        return new ResponseEntity<>(new SensorDTO(sensorService.saveSensor(newSensor)),HttpStatus.CREATED);
    }

}
