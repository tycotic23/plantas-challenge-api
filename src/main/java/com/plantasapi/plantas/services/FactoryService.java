package com.plantasapi.plantas.services;

import com.plantasapi.plantas.dtos.FactoryDTO;
import com.plantasapi.plantas.dtos.FactoryInfoDTO;
import com.plantasapi.plantas.models.Factory;

import java.util.List;

public interface FactoryService {
    List<Factory> findAllFactories();

    List<FactoryInfoDTO> findAllFactoriesDTO();
    List<Factory> findAllUserFactories(String username);

    List<FactoryInfoDTO> findAllUserFactoriesDTO(String username);

    List<FactoryDTO> findAllUserFactoriesDTOWithSensors(String username);
    Factory saveFactory(Factory factory);

    void deleteFactory(long id);

    boolean existsByUser_usernameAndId(String username,long id);
    Factory findFactory(long id);

    FactoryDTO findFactoryDTO(long id);

    Factory findFactoryUser(String username,long id);
    FactoryDTO findFactoryUserDTO(String username,long id);
    Factory updateFactory(long id,Factory newFactory);
}
