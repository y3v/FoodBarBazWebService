package com.foodbarbaz;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
// take user as reference
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity //mark this class as an entity
public class UserLocation {
	
	@Id @GeneratedValue
	private long id;
	private long userId;
	private double latitude;
	private double longitude;
	private Date timestamp;
	
	public UserLocation(long id, double latitude, double longitude, Date timestamp) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.timestamp = timestamp;
	}
	
	public UserLocation() {}
	
		
}
