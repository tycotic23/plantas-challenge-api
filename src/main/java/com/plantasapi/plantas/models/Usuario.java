package com.plantasapi.plantas.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {
    /*por ahora el nombre está así porque parece que si se llama User genera un error por la tabla del mismo nombre en h2-hibernate*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @SequenceGenerator( name="native")
    private long id;

    private String name;
    private String email;

    private String password;
    private String picture;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Factory> factories=new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<RecordFactory> recordFactories=new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<RecordSensor> recordSensors=new HashSet<>();

    public Usuario() {
    }

    public Usuario(String name, String email, String password, String picture) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Set<Factory> getFactories() {
        return factories;
    }

    public void addFactory(Factory factory) {
        factory.setUser(this);
        factories.add(factory);
    }

    public Set<RecordFactory> getRecordFactories() {
        return recordFactories;
    }

    public void addRecordFactories(RecordFactory recordFactory) {
        recordFactory.setUser(this);
        recordFactories.add(recordFactory);
    }

    public Set<RecordSensor> getRecordSensors() {
        return recordSensors;
    }

    public void addRecordSensors(RecordSensor recordSensor) {
        recordSensor.setUser(this);
        recordSensors.add(recordSensor);
    }
}
