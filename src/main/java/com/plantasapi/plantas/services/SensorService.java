package com.plantasapi.plantas.services;

import com.plantasapi.plantas.models.Sensor;

import java.util.List;

public interface SensorService {

    List<Sensor> findAllSensors();
    Sensor saveSensor(Sensor sensor);

    void deleteSensor(long id);
    Sensor findSensor(long id);
    Sensor updateSensor(long id,Sensor newSensor);

    boolean existsByFactory_idAndByType_id(long factory_id,long type_id);
    void deleteByFactory_idAndType_id(long factory_id,long type_id);
}
