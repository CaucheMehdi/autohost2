package com.autohost.customer.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bill implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -618618342525996288L;
	@Id
    @GeneratedValue
	private Long 		id;
	private Customer 	customer;
	private Ressource		plan;
	private Date 		startDate;
	private Date 		endDate;
	private Date 		facturation;
	private Boolean		paid;
	
	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bill(Long id, Customer customer, Date startDate, Date endDate, Date facturation, Boolean paid) {
		super();
		this.id = id;
		this.customer = customer;
		this.startDate = startDate;
		this.endDate = endDate;
		this.facturation = facturation;
		this.paid = paid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getFacturation() {
		return facturation;
	}

	public void setFacturation(Date facturation) {
		this.facturation = facturation;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}
	
	

}
