package com.plantasapi.plantas.repositories;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface FactoryRepository extends JpaRepository<Factory,Long> {
    List<Factory> findByUser_username(String username);
    Optional<Factory> findByUser_usernameAndId(String username,long id);
    void deleteByUser_usernameAndId(String username,long id);
}
