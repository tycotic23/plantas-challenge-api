package com.plantasapi.plantas.repositories;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.RecordSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface RecordSensorRepository extends JpaRepository<RecordSensor,Long> {
    List<RecordSensor> findByUser_username(String username);
}
