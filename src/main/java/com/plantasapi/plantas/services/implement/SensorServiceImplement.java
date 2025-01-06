package com.plantasapi.plantas.services.implement;

import com.plantasapi.plantas.models.Sensor;
import com.plantasapi.plantas.repositories.SensorRepository;
import com.plantasapi.plantas.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                    sensor.setDisabledSensors(newSensor.getDisabledSensors());
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
}
