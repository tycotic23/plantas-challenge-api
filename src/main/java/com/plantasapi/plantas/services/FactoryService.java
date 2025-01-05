package com.plantasapi.plantas.services;

import com.plantasapi.plantas.models.Factory;

import java.util.List;

public interface FactoryService {
    List<Factory> findAllFactories();
    List<Factory> findAllUserFactories(long userId);
    Factory saveFactory(Factory factory);

    void deleteFactory(long id);
    Factory findFactory(long id);
    Factory updateFactory(long id,Factory newFactory);
}
