package com.plantasapi.plantas.dtos;

import com.plantasapi.plantas.models.Factory;
import com.plantasapi.plantas.models.Sensor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class FactoryDTO {
    private long id;
    private String country;

    private String flag;
    private String name;
    private int readings;
    private int medium_alerts;
    private int red_alerts;

    private int disabled;

    private Set<SensorDTO> sensors=new HashSet<>();

    public FactoryDTO(Factory factory){
        id=factory.getId();
        name= factory.getName();
        country=factory.getCountry();
        flag=factory.getFlag();
        readings=factory.getSensors().stream().mapToInt(Sensor::getReadings).reduce(0,Integer::sum);
        medium_alerts=factory.getSensors().stream().mapToInt(Sensor::getMediumAlerts).reduce(0,Integer::sum);
        red_alerts=factory.getSensors().stream().mapToInt(Sensor::getRedAlerts).reduce(0,Integer::sum);
        disabled =factory.getSensors().stream().mapToInt(Sensor::getDisabled).reduce(0,Integer::sum);
        sensors=factory.getSensors().stream().map(SensorDTO::new).collect(Collectors.toSet());
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

    public int getDisabled() {
        return disabled;
    }

    public Set<SensorDTO> getSensors() {
        return sensors;
    }

    public String getFlag() {
        return flag;
    }
}
