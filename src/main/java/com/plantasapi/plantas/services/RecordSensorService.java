package com.plantasapi.plantas.services;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.RecordSensor;

import java.util.List;

public interface RecordSensorService {
    List<RecordSensor> findAllRecordSensors();
}
