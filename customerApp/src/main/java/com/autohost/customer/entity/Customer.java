package com.autohost.customer.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "id", "trackerId" }))
@Entity
public class Customer implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1207890237183815113L;

    @Id
    @GeneratedValue
    private Long id;

    private String trackerId;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private String email;

    @Transient
    private List<Bill> billHistory;

    public Customer() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Customer(String name, String surname, String phone, String email) {
        super();
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public String getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(String trackerId) {
        this.trackerId = trackerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Bill> getBillHistory() {
        return billHistory;
    }

    public void setBillHistory(List<Bill> billHistory) {
        this.billHistory = billHistory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
