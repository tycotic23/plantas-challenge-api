package com.plantasapi.plantas.services.implement;

import com.plantasapi.plantas.dtos.RecordDTO;
import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.RecordSensor;
import com.plantasapi.plantas.repositories.RecordSensorRepository;
import com.plantasapi.plantas.services.RecordSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordSensorServiceImplement implements RecordSensorService {
    @Autowired
    RecordSensorRepository recordSensorRepository;

    @Override
    public List<RecordSensor> findAllRecordSensors() {
        return recordSensorRepository.findAll();
    }

    @Override
    public List<RecordSensor> findByUser_username(String username) {
        return recordSensorRepository.findByUser_username(username);
    }

    @Override
    public RecordSensor saveRecordSensor(RecordSensor recordSensor) {
        return recordSensorRepository.save(recordSensor);
    }

    @Override
    public List<RecordDTO> findByUser_usernameDTO(String username) {
        return findByUser_username(username).stream().map(RecordDTO::new).collect(Collectors.toList());
    }
}
