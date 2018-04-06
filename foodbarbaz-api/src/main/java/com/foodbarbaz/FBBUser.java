package com.foodbarbaz;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity //mark this class as an entity
public class FBBUser {
	
	@Id @GeneratedValue
	private long id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	
	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "requester")
	 private Set<Friendship> friendRequests = new HashSet<>();

	 @OneToMany(fetch = FetchType.EAGER, mappedBy = "friend")
	 private Set<Friendship> friends = new HashSet<>();
	
	
	public FBBUser(long id, String username, String password, String firstname, String lastname,
			String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	
	public FBBUser() {} //default constructor

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Friendship> getFriendRequests() {
		return friendRequests;
	}

	public void setFriendRequests(Set<Friendship> friendRequests) {
		this.friendRequests = friendRequests;
	}

	public Set<Friendship> getFriends() {
		return friends;
	}

	public void setFriends(Set<Friendship> friends) {
		this.friends = friends;
	}
	
	
	
	
	
}
