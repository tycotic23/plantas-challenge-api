package com.plantasapi.plantas.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class TypeSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @SequenceGenerator( name="native")
    private long id;

    private String type;

    @OneToMany(mappedBy = "type",fetch = FetchType.EAGER)
    private Set<Sensor> sensors=new HashSet<>();

    public TypeSensor() {
    }

    public TypeSensor(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void addSensor(Sensor sensor) {
        sensor.setType(this);
        sensors.add(sensor);
    }
}
