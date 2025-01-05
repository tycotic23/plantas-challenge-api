package com.plantasapi.plantas.services.implement;

import com.plantasapi.plantas.models.TypeSensor;
import com.plantasapi.plantas.services.TypeSensorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeSensorServiceImplement implements TypeSensorService {
    @Override
    public List<TypeSensor> findAllTypeSensors() {
        return null;
    }
}
