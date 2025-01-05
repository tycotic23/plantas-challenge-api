package com.plantasapi.plantas.services.implement;

import com.plantasapi.plantas.models.Sensor;
import com.plantasapi.plantas.services.SensorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorServiceImplement implements SensorService {
    @Override
    public List<Sensor> findAllSensors() {
        return null;
    }

    @Override
    public Sensor saveSensor(Sensor Sensor) {
        return null;
    }

    @Override
    public void deleteSensor(long id) {

    }

    @Override
    public Sensor findSensor(long id) {
        return null;
    }

    @Override
    public Sensor updateSensor(long id, Sensor newSensor) {
        return null;
    }
}
