package com.plantasapi.plantas.services.implement;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.RecordSensor;
import com.plantasapi.plantas.repositories.RecordSensorRepository;
import com.plantasapi.plantas.services.RecordSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordSensorServiceImplement implements RecordSensorService {
    @Autowired
    RecordSensorRepository recordSensorRepository;

    @Override
    public List<RecordSensor> findAllRecordSensors() {
        return recordSensorRepository.findAll();
    }
}
