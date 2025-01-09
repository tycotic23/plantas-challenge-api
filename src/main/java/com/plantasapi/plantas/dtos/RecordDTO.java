package com.plantasapi.plantas.dtos;

import com.plantasapi.plantas.models.RecordFactory;
import com.plantasapi.plantas.models.RecordSensor;

import java.time.LocalDateTime;

public class RecordDTO {
    private long id;
    private String action;
    private LocalDateTime date;

    public RecordDTO(RecordFactory recordFactory){
        id=recordFactory.getId();
        action= recordFactory.getAction();
        date=recordFactory.getDate();

    }

    public RecordDTO(RecordSensor recordSensor){
        id=recordSensor.getId();
        action= recordSensor.getAction();
        date=recordSensor.getDate();

    }

    public long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
