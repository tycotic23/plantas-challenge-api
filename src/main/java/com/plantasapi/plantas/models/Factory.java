package com.plantasapi.plantas.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
public class Factory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @SequenceGenerator( name="native")
    private long id;

    private String country;
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Usuario user;

    @OneToMany(mappedBy = "factory",fetch = FetchType.EAGER)
    private Set<Sensor> sensors=new HashSet<>();

    public Factory() {
    }

    public Factory(String country, String name) {
        this.country = country;
        this.name = name;
    }

    public long getId() {
        return id;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario usuario) {
        this.user = usuario;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void addSensor(Sensor sensor) {
        sensor.setFactory(this);
        sensors.add(sensor);
    }
}

