package com.plantasapi.plantas.services;

import com.plantasapi.plantas.dtos.SensorDTO;
import com.plantasapi.plantas.models.Sensor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SensorService {

    List<Sensor> findAllSensors();
    Sensor saveSensor(Sensor sensor);

    void deleteSensor(long id);
    Sensor findSensor(long id);
    Sensor updateSensor(long id,Sensor newSensor);

    Sensor findByFactory_user_usernameAndFactory_idAndType_type(String username,long factory_id,String type);

    boolean existsByFactory_idAndByType_id(long factory_id,long type_id);
    void deleteByFactory_idAndType_id(long factory_id,long type_id);

    Sensor findByFactory_user_usernameAndId(String username, long id);

    Map<String,SensorDTO> groupByType(String username);
    Map<String,SensorDTO> FindByFactory_idAndGroupByType(String username,long id);

    SensorDTO reduceAll(String username);
}
