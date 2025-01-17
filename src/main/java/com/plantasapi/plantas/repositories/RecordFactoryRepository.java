package com.plantasapi.plantas.repositories;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.RecordFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface RecordFactoryRepository extends JpaRepository<RecordFactory,Long> {
    List<RecordFactory> findByUser_username(String username);
}
