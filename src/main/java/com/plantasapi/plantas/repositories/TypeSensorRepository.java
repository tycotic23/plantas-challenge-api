package com.plantasapi.plantas.repositories;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.TypeSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface TypeSensorRepository extends JpaRepository<TypeSensor,Long> {
    Optional<TypeSensor> findByType(String type);
}
