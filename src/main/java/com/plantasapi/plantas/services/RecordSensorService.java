package com.plantasapi.plantas.services;

import com.plantasapi.plantas.models.Factory;

import java.util.List;

public interface RecordSensorService {
    List<Factory> findAllRecordSensors();
}