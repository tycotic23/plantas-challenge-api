package com.plantasapi.plantas.models;

import jakarta.persistence.*;

@Entity
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @SequenceGenerator( name="native")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private TypeSensor type;
    private int readings;
    private int mediumAlerts;
    private int redAlerts;
    private int disabledSensors;

    public Sensor() {
    }

    public Sensor(int readings, int mediumAlerts, int redAlerts, int disabledSensors) {
        this.readings = readings;
        this.mediumAlerts = mediumAlerts;
        this.redAlerts = redAlerts;
        this.disabledSensors = disabledSensors;
    }

    public long getId() {
        return id;
    }

    public TypeSensor getType() {
        return type;
    }

    public int getReadings() {
        return readings;
    }

    public void setReadings(int readings) {
        this.readings = readings;
    }

    public int getMediumAlerts() {
        return mediumAlerts;
    }

    public void setMediumAlerts(int mediumAlerts) {
        this.mediumAlerts = mediumAlerts;
    }

    public int getRedAlerts() {
        return redAlerts;
    }

    public void setRedAlerts(int redAlerts) {
        this.redAlerts = redAlerts;
    }

    public int getDisabledSensors() {
        return disabledSensors;
    }

    public void setDisabledSensors(int disabledSensors) {
        this.disabledSensors = disabledSensors;
    }

    public void setType(TypeSensor type) {
        this.type = type;
    }
}
