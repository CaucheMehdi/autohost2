package com.autohost.customer.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
/**
 * Représente une commande client,
 * l'arribut status permet de spécifier le status de la commande (CREATION,CREATED),
 * entité sauvegardé en db.
 * @author mehdi
 *
 */
@Entity
public class Ordering {
	@Id
    @GeneratedValue
	private Long id;
	private String trackerIdCustomer;
	private String trackerIdPlan;
	private String trackerIdPanType;
	private String status;
	private Date createdAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTrackerIdCustomer() {
		return trackerIdCustomer;
	}
	public void setTrackerIdCustomer(String trackerIdCustomer) {
		this.trackerIdCustomer = trackerIdCustomer;
	}
	public String getTrackerIdPlan() {
		return trackerIdPlan;
	}
	public void setTrackerIdPlan(String trackerIdPlan) {
		this.trackerIdPlan = trackerIdPlan;
	}
	public String getTrackerIdPlanType() {
		return trackerIdPanType;
	}
	public void setTrackerIdPanType(String trackerIdPanType) {
		this.trackerIdPanType = trackerIdPanType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Ordering() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	

}
