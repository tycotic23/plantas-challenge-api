package com.plantasapi.plantas.services.implement;

import com.plantasapi.plantas.dtos.RecordDTO;
import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.RecordFactory;
import com.plantasapi.plantas.repositories.RecordFactoryRepository;
import com.plantasapi.plantas.services.RecordFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordFactoryServiceImplement implements RecordFactoryService {

    @Autowired
    RecordFactoryRepository recordFactoryRepository;

    @Override
    public List<RecordFactory> findAllRecordFactories() {
        return recordFactoryRepository.findAll();
    }

    @Override
    public List<RecordFactory> findByUser_username(String username) {
        return recordFactoryRepository.findByUser_username(username);
    }

    @Override
    public RecordFactory saveRecordFactory(RecordFactory recordFactory) {
        return recordFactoryRepository.save(recordFactory);
    }

    @Override
    public List<RecordDTO> findByUser_usernameDTO(String username) {
        return findByUser_username(username).stream().map(RecordDTO::new).collect(Collectors.toList());
    }
}
