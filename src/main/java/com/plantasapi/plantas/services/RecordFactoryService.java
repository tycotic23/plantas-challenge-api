package com.plantasapi.plantas.services;

import com.plantasapi.plantas.dtos.RecordDTO;
import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.RecordFactory;

import java.util.List;

public interface RecordFactoryService {
    List<RecordFactory> findAllRecordFactories();
    List<RecordFactory> findByUser_username(String username);
    List<RecordDTO> findByUser_usernameDTO(String username);

    RecordFactory saveRecordFactory(RecordFactory recordFactory);
}
