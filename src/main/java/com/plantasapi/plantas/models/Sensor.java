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
    private int disabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "factory_id")
    private Factory factory;

    public Sensor() {
    }

    public Sensor(int readings, int mediumAlerts, int redAlerts, int disabled) {
        this.readings = readings;
        this.mediumAlerts = mediumAlerts;
        this.redAlerts = redAlerts;
        this.disabled = disabled;
    }

    public Sensor(int readings, int mediumAlerts, int redAlerts) {
        this.readings = readings;
        this.mediumAlerts = mediumAlerts;
        this.redAlerts = redAlerts;
        this.disabled = 0;
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

    public int getDisabled() {
        return disabled;
    }

    public void setDisabled(int disabled) {
        this.disabled = disabled;
    }

    public void setType(TypeSensor type) {
        this.type = type;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }
}
