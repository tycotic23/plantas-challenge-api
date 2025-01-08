package com.plantasapi.plantas.services.implement;

import com.plantasapi.plantas.dtos.FactoryDTO;
import com.plantasapi.plantas.dtos.FactoryInfoDTO;
import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.repositories.FactoryRepository;
import com.plantasapi.plantas.services.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FactoryServiceImplement implements FactoryService {
    @Autowired
    private FactoryRepository factoryRepository;

    @Override
    public List<Factory> findAllFactories() {
        return factoryRepository.findAll();
    }

    @Override
    public List<FactoryInfoDTO> findAllFactoriesDTO() {
        return findAllFactories().stream().map(FactoryInfoDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<FactoryInfoDTO> findAllUserFactoriesDTO(String username) {
        return findAllUserFactories(username).stream().map(FactoryInfoDTO::new).collect(Collectors.toList());
    }

    @Override
    public Factory saveFactory(Factory factory) {
        return factoryRepository.save(factory);
    }

    @Override
    public Factory updateFactory(long id,Factory newFactory) {
        return factoryRepository.findById(id)
                .map(factory->{
                  factory.setName(newFactory.getName());
                  factory.setCountry(newFactory.getCountry());
                  return factoryRepository.save(factory);
                }).orElseGet(()->{
                    return factoryRepository.save(newFactory);
                });
    }

    @Override
    public void deleteFactory(long id) {
        factoryRepository.deleteById(id);
    }

    @Override
    public Factory findFactory(long id) {
        return factoryRepository.findById(id).orElse(null);
    }

    @Override
    public FactoryDTO findFactoryDTO(long id) {
        return new FactoryDTO(findFactory(id));
    }

    @Override
    public List<Factory> findAllUserFactories(String username) {
        return factoryRepository.findByUser_username(username);
    }

    @Override
    public Factory findFactoryUser(String username, long id) {
        return factoryRepository.findByUser_usernameAndId(username,id).orElse(null);
    }

    @Override
    public FactoryDTO findFactoryUserDTO(String username, long id) {
        return new FactoryDTO(findFactoryUser(username,id));
    }

    @Override
    public void deleteUserFactory(String username, long id) {
        factoryRepository.deleteByUser_usernameAndId(username,id);
    }
}
