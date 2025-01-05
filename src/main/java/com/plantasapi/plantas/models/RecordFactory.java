package com.plantasapi.plantas.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RecordFactory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @SequenceGenerator( name="native")
    private long id;

    private String action;
    private LocalDateTime date;

    public RecordFactory() {
    }

    public RecordFactory(String action, LocalDateTime date) {
        this.action = action;
        this.date = date;
    }

    public long getId() {
        return id;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
