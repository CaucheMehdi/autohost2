package com.autohost.customer.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import entityDTO.RessourceDTO;

/**
 * Représente une ressource
 * @author amsdev
 *
 */
@Entity
public class Ressource implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3235872982217176414L;
    @Id
    @GeneratedValue
    private Long              id;
    private String            size;
    private String            application;
    private String            os;
    private String            place;
    private String            provider;
    private String            ip;
    private String            status;
    private String            trackingId;

    /**
     * Chaque ressource est alloué à un client
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "trackerId")
    private Customer customer;

    public Ressource() {
        super();
    }

    public Ressource(RessourceDTO resDto, Customer c) {
        super();

        this.size = resDto.getSize();
        this.application = resDto.getApplication();
        this.os = resDto.getOs();
        this.place = resDto.getPlace();
        this.provider = resDto.getProvider();
        this.ip = resDto.getIp();
        this.status = resDto.getStatus();
        this.trackingId = resDto.getTrackingId();
        this.customer = c;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @return the application
     */
    public String getApplication() {
        return application;
    }

    /**
     * @return the os
     */
    public String getOs() {
        return os;
    }

    /**
     * @return the place
     */
    public String getPlace() {
        return place;
    }

    /**
     * @return the provider
     */
    public String getProvider() {
        return provider;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return the clientTid
     */

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @param application the application to set
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * @param os the os to set
     */
    public void setOs(String os) {
        this.os = os;
    }

    /**
     * @param place the place to set
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(String provider) {
        this.provider = provider;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the trackingId
     */
    public String getTrackingId() {
        return trackingId;
    }

    /**
     * @param trackingId the trackingId to set
     */
    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

}