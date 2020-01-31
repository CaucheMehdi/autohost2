package com.autohost.managerApp.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ressource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String  size;
    private String  configuration;
    private String  os;
    private String  place;
    private String  provider;
    private String  trackerId;
    private String  ip;
    private Boolean attribued;

    public Ressource() {
    }

    public Ressource(String ip, String size, String os, String provider, String configuration, Boolean attribued) {
        super();
        this.ip = ip;
        this.size = size;
        this.os = os;
        this.provider = provider;
        this.configuration = configuration;
        this.attribued = attribued;
    }

    public String getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(String trackerId) {
        this.trackerId = trackerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public Boolean getAttribued() {
        return attribued;
    }

    public void setAttribued(Boolean attribued) {
        this.attribued = attribued;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

}
