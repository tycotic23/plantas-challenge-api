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

    Optional<Sensor> findByFactory_user_usernameAndFactory_idAndType_type(String username,long factory_id,String type_type);

    Optional<Sensor> findByFactory_user_usernameAndId(String username, long id);


    List<Sensor> findByFactory_user_username(String username);
    List<Sensor> findByFactory_user_usernameAndFactory_id(String username,long factory_id);
}
