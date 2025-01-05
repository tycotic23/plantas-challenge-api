package com.plantasapi.plantas.services.implement;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.RecordFactory;
import com.plantasapi.plantas.repositories.RecordFactoryRepository;
import com.plantasapi.plantas.services.RecordFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordFactoryServiceImplement implements RecordFactoryService {

    @Autowired
    RecordFactoryRepository recordFactoryRepository;

    @Override
    public List<RecordFactory> findAllRecordFactories() {
        return recordFactoryRepository.findAll();
    }
}
