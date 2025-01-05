package com.plantasapi.plantas.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RecordSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @SequenceGenerator( name="native")
    private long id;

    private String action;
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Usuario user;

    public RecordSensor() {
    }

    public RecordSensor(String action, LocalDateTime date) {
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

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}
