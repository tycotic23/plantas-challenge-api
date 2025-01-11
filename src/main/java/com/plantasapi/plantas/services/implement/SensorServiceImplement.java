package com.plantasapi.plantas.services.implement;

import com.plantasapi.plantas.dtos.SensorDTO;
import com.plantasapi.plantas.models.Sensor;
import com.plantasapi.plantas.repositories.SensorRepository;
import com.plantasapi.plantas.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SensorServiceImplement implements SensorService {
    @Autowired
    SensorRepository sensorRepository;

    @Override
    public List<Sensor> findAllSensors() {
        return sensorRepository.findAll();
    }

    @Override
    public Sensor saveSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public void deleteSensor(long id) {
        sensorRepository.deleteById(id);
    }

    @Override
    public Sensor findSensor(long id) {
        return sensorRepository.findById(id).orElse(null);
    }

    @Override
    public Sensor updateSensor(long id, Sensor newSensor) {
        return sensorRepository.findById(id)
                .map(sensor->{
                    sensor.setDisabled(newSensor.getDisabled());
                    sensor.setMediumAlerts(newSensor.getMediumAlerts());
                    sensor.setReadings(newSensor.getReadings());
                    sensor.setRedAlerts(newSensor.getRedAlerts());
                    return sensorRepository.save(sensor);
                }).orElseGet(()->{
                   return  sensorRepository.save(newSensor);
                });
    }

    @Override
    public boolean existsByFactory_idAndByType_id(long factory_id, long type_id) {
        return sensorRepository.existsByFactory_idAndType_id(factory_id,type_id);
    }

    @Override
    public void deleteByFactory_idAndType_id(long factory_id, long type_id) {
        sensorRepository.deleteByFactory_idAndType_id(factory_id,type_id);
    }

    @Override
    public Sensor findByFactory_user_usernameAndId(String username, long id) {
        return sensorRepository.findByFactory_user_usernameAndId(username,id).orElse(null);
    }

    @Override
    public Map<String,SensorDTO> groupByType(String username) {

        Map<String,List<SensorDTO>> groupedSensorsDTO=sensorRepository.findByFactory_user_username(username).stream()
                .map(SensorDTO::new)
                .collect(Collectors.groupingBy(SensorDTO::getType));

        Map<String,SensorDTO> reducedSensorsDTO=new HashMap<>();

        groupedSensorsDTO.forEach(
                (type,sensors)-> {
                    //el id es -1 para que no coincida con ninguno de la bdd, para evitar posibles errores
                    SensorDTO acum=new SensorDTO(-1, type, 0, 0, 0, 0);
                    sensors.forEach(acum::sum);
                    reducedSensorsDTO.put(type,acum);
                }
        );

        return reducedSensorsDTO;
    }

    @Override
    public Map<String,SensorDTO> FindByFactory_idAndGroupByType(String username,long id) {

        Map<String,List<SensorDTO>> groupedSensorsDTO=sensorRepository.findByFactory_user_usernameAndFactory_id(username,id).stream()
                .map(SensorDTO::new)
                .collect(Collectors.groupingBy(SensorDTO::getType));

        Map<String,SensorDTO> reducedSensorsDTO=new HashMap<>();

        groupedSensorsDTO.forEach(
                (type,sensors)-> {
                    //el id es -1 para que no coincida con ninguno de la bdd, para evitar posibles errores
                    SensorDTO acum=new SensorDTO(-1, type, 0, 0, 0, 0);
                    sensors.forEach(acum::sum);
                    reducedSensorsDTO.put(type,acum);
                }
        );

        return reducedSensorsDTO;
    }

    @Override
    public SensorDTO reduceAll(String username) {
        return sensorRepository.findByFactory_user_username(username).stream().map(SensorDTO::new).reduce(new SensorDTO(-1, "type", 0, 0, 0, 0),(a,b)-> {
            return new SensorDTO(-1,
                    b.getType(),
                    a.getReadings()+b.getReadings(),
                    a.getMedium_alerts()+b.getMedium_alerts(),
                    a.getRed_alerts()+b.getRed_alerts(),
                    a.getDisabled()+b.getDisabled());
        });
    }

    @Override
    public Sensor findByFactory_user_usernameAndFactory_idAndType_type(String username,long factory_id, String type) {
        return sensorRepository.findByFactory_user_usernameAndFactory_idAndType_type(username,factory_id, type).orElse(null);
    }
}
