package com.plantasapi.plantas.services;

import com.plantasapi.plantas.models.TypeSensor;

import java.util.List;
import java.util.Optional;

public interface TypeSensorService {
    List<TypeSensor> findAllTypeSensors();

    TypeSensor findByType(String type);

}
