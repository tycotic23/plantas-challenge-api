package com.plantasapi.plantas.repositories;

import com.plantasapi.plantas.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SensorRepository extends JpaRepository<Sensor,Long> {
    boolean existsByFactory_idAndType_id(long factory_id,long type_id);
    void deleteByFactory_idAndType_id(long factory_id,long type_id);
}
