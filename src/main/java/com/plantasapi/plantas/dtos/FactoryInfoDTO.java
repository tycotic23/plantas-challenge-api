package com.plantasapi.plantas.dtos;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.Sensor;

public class FactoryInfoDTO {
    private long id;
    private String country;
    private String name;
    private int readings;
    private int medium_alerts;
    private int red_alerts;

    public FactoryInfoDTO(Factory factory){
        id=factory.getId();
        name= factory.getName();
        country=factory.getCountry();
        readings=factory.getSensors().stream().mapToInt(Sensor::getReadings).reduce(0,Integer::sum);
        medium_alerts=factory.getSensors().stream().mapToInt(Sensor::getMediumAlerts).reduce(0,Integer::sum);
        red_alerts=factory.getSensors().stream().mapToInt(Sensor::getRedAlerts).reduce(0,Integer::sum);
    }

    public long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
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
}
