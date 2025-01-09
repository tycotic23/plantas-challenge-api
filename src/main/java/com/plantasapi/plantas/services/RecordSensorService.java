package com.plantasapi.plantas.services;

import com.plantasapi.plantas.dtos.RecordDTO;
import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.RecordFactory;
import com.plantasapi.plantas.models.RecordSensor;

import java.util.List;

public interface RecordSensorService {
    List<RecordSensor> findAllRecordSensors();
    List<RecordSensor> findByUser_username(String username);
    List<RecordDTO> findByUser_usernameDTO(String username);
    RecordSensor saveRecordSensor(RecordSensor recordSensor);
}
