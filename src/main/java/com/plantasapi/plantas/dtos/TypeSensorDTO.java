package com.plantasapi.plantas.dtos;

import com.plantasapi.plantas.models.Sensor;
import com.plantasapi.plantas.models.TypeSensor;

public class TypeSensorDTO {
    private long id;
    private String type;
    private int readings;
    private int medium_alerts;
    private int red_alerts;

    private int disabled;



    public TypeSensorDTO(TypeSensor typeSensor){
        id=typeSensor.getId();
        type=typeSensor.getType();
        readings=typeSensor.getSensors().stream().mapToInt(Sensor::getReadings).reduce(0,Integer::sum);
        medium_alerts=typeSensor.getSensors().stream().mapToInt(Sensor::getMediumAlerts).reduce(0,Integer::sum);
        red_alerts=typeSensor.getSensors().stream().mapToInt(Sensor::getRedAlerts).reduce(0,Integer::sum);
        disabled = typeSensor.getSensors().stream().mapToInt(Sensor::getDisabled).reduce(0,Integer::sum);
    }

    public long getId() {
        return id;
    }

    public int getReadings() {
        return readings;
    }

    public int getMedium_alerts() {
        return medium_alerts;
    }

    public int getRed_alerts() {
        return red_alerts;
    }

    public String getType() {
        return type;
    }

    public int getDisabled() {
        return disabled;
    }
}
