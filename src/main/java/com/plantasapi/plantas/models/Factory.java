package com.plantasapi.plantas.models;

import jakarta.persistence.*;


@Entity
public class Factory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @SequenceGenerator( name="native")
    private long id;

    private String country;
    private String name;

    public Factory() {
    }

    public Factory(String country, String name) {
        this.country = country;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}

