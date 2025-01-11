package com.plantasapi.plantas.dtos;

import com.plantasapi.plantas.models.Sensor;

public class SensorCreateDTO {
    private long id;
    private String type;

    private long factory_id;
    private int readings;
    private int medium_alerts;
    private int red_alerts;


    public SensorCreateDTO() {
    }

    public SensorCreateDTO(String type,long factory_id, int readings, int medium_alerts, int red_alerts) {

        this.type = type;
        this.factory_id=factory_id;
        this.readings = readings;
        this.medium_alerts = medium_alerts;
        this.red_alerts = red_alerts;
    }

    public SensorCreateDTO(Sensor sensor){
        id=sensor.getId();
        this.factory_id=sensor.getFactory().getId();
        type=sensor.getType().getType();
        readings=sensor.getReadings();
        medium_alerts=sensor.getMediumAlerts();
        red_alerts=sensor.getRedAlerts();
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


    public void setType(String type) {
        this.type = type;
    }

    public void setReadings(int readings) {
        this.readings = readings;
    }

    public void setMedium_alerts(int medium_alerts) {
        this.medium_alerts = medium_alerts;
    }

    public void setRed_alerts(int red_alerts) {
        this.red_alerts = red_alerts;
    }


    public long getFactory_id() {
        return factory_id;
    }

    public void setFactory_id(long factory_id) {
        this.factory_id = factory_id;
    }
}
