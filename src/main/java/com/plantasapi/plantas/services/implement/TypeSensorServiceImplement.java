package com.plantasapi.plantas.services.implement;

import com.plantasapi.plantas.models.TypeSensor;
import com.plantasapi.plantas.repositories.TypeSensorRepository;
import com.plantasapi.plantas.services.TypeSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeSensorServiceImplement implements TypeSensorService {
    @Autowired
    TypeSensorRepository typeSensorRepository;

    @Override
    public List<TypeSensor> findAllTypeSensors() {
        return typeSensorRepository.findAll();
    }

    @Override
    public TypeSensor findByType(String type) {
        return typeSensorRepository.findByType(type).orElse(null);
    }

    @Override
    public boolean existsByType(String type) {
        return typeSensorRepository.existsByType(type);
    }
}
