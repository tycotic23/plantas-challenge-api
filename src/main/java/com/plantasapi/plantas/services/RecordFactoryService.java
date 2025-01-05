package com.plantasapi.plantas.services;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.RecordFactory;

import java.util.List;

public interface RecordFactoryService {
    List<RecordFactory> findAllRecordFactories();
}
