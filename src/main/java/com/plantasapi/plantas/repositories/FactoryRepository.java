package com.plantasapi.plantas.repositories;

import com.plantasapi.plantas.models.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FactoryRepository extends JpaRepository<Factory,Long> {
}
