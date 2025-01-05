package com.plantasapi.plantas.services.implement;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.repositories.FactoryRepository;
import com.plantasapi.plantas.services.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryServiceImplement implements FactoryService {
    @Autowired
    private FactoryRepository factoryRepository;

    @Override
    public List<Factory> findAllFactories() {
        return factoryRepository.findAll();
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
}
