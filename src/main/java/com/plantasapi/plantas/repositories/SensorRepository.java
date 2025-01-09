package com.plantasapi.plantas.repositories;

import com.plantasapi.plantas.dtos.SensorDTO;
import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface SensorRepository extends JpaRepository<Sensor,Long> {
    boolean existsByFactory_idAndType_id(long factory_id,long type_id);
    void deleteByFactory_idAndType_id(long factory_id,long type_id);

    Optional<Sensor> findByFactory_user_usernameAndId(String username, long id);

    //@Query("select type.type, sum(readings) as readings, sum(mediumAlerts) as medium_alerts,sum(redAlerts) as red_alerts,sum(disabledSensors) as disabled_sensors from Sensor group by type.type")
    @Query(value = "select id,readings,type_id as type, medium_Alerts,red_Alerts,disabled_Sensors from Sensor",nativeQuery = true)
    List<SensorDTO> groupByType();
}
