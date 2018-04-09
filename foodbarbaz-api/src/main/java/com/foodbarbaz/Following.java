package com.foodbarbaz;

import java.io.Serializable;
import java.util.Calendar;

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
@Table(name = "following")
public class Following implements Serializable {

	@Id @GeneratedValue
	@Column(name = "id", updatable = false, nullable = false)
	private long id;
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
	private java.util.Date timestamp;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private FBBUser follower;
	
	private long friendId;
	
	public Following() {
		this.timestamp = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}

	public java.util.Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(java.util.Date timestamp) {
		this.timestamp = timestamp;
	}

	public FBBUser getFollower() {
		return follower;
	}

	public void setFollower(FBBUser follower) {
		this.follower = follower;
	}
	
}
