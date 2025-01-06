package com.plantasapi.plantas.dtos;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.Sensor;

public class SensorDTO {
    private long id;
    private String type;
    private int readings;
    private int medium_alerts;
    private int red_alerts;

    private int disabled_sensors;


    public SensorDTO(Sensor sensor){
        id=sensor.getId();
        type=sensor.getType().getType();
        readings=sensor.getReadings();
        medium_alerts=sensor.getMediumAlerts();
        red_alerts=sensor.getRedAlerts();
        disabled_sensors= sensor.getDisabledSensors();
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

    public int getDisabled_sensors() {
        return disabled_sensors;
    }
}
