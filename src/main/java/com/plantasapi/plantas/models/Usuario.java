package com.plantasapi.plantas.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario implements UserDetails {
    /*por ahora el nombre está así porque parece que si se llama User genera un error por la tabla del mismo nombre en h2-hibernate*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @SequenceGenerator( name="native")
    private long id;

    private String username;
    private String email;

    private String password;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<Factory> factories=new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<RecordFactory> recordFactories=new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<RecordSensor> recordSensors=new HashSet<>();

    public Usuario() {
    }

    public Usuario(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
