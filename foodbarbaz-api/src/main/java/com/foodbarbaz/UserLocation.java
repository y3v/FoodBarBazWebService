package com.foodbarbaz;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity //mark this class as an entity
@Table(name = "user_location")
public class UserLocation implements Serializable{
	@Id @GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	private long id;
	
	private double latitude;
	
	private double longitude;
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
	private java.util.Date timestamp;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private FBBUser user;
	
	public UserLocation(double latitude, double longitude, FBBUser user) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = new java.util.Date(Calendar.getInstance().getTime().getTime());
		if (user != null)
			this.user = user;
	}
	
	public UserLocation() {
		this.timestamp = new java.util.Date(Calendar.getInstance().getTime().getTime());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public java.util.Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(java.util.Date timestamp) {
		this.timestamp = timestamp;
	}

	public FBBUser getUser() {
		return user;
	}

	public void setUser(FBBUser user) {
		this.user = user;
	}
	
	
		
}
