package com.plantasapi.plantas.dtos;

import com.plantasapi.plantas.models.Sensor;

public class SensorCreateDTO {
    private long id;
    private String type;
    private int readings;
    private int medium_alerts;
    private int red_alerts;

    private int disabled_sensors;

    public SensorCreateDTO() {
    }

    public SensorCreateDTO(String type, int readings, int medium_alerts, int red_alerts, int disabled_sensors) {

        this.type = type;
        this.readings = readings;
        this.medium_alerts = medium_alerts;
        this.red_alerts = red_alerts;
        this.disabled_sensors = disabled_sensors;
    }

    public SensorCreateDTO(Sensor sensor){
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

    public void setDisabled_sensors(int disabled_sensors) {
        this.disabled_sensors = disabled_sensors;
    }
}
