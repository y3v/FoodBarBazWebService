package com.foodbarbaz;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity //mark this class as an entity
public class FBBUser implements Serializable{
	
	@Id @GeneratedValue
	private long id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String email;

	@ManyToMany(cascade={CascadeType.ALL})
    @JoinTable(name="EMPLOYEE_COLLEAGUE",
        joinColumns={@JoinColumn(name="FRIEND1_ID")},
        inverseJoinColumns={@JoinColumn(name="FRIEND2_ID")})
    private Set<FBBUser> friends = new HashSet<FBBUser>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<UserLocation> userLocations = new HashSet<>();
	
	
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


	public Set<FBBUser> getFriends() {
		return friends;
	}

	public void setFriends(Set<FBBUser> friends) {
		this.friends = friends;
	}

	public Set<UserLocation> getUserLocations() {
		return userLocations;
	}

	public void setUserLocations(Set<UserLocation> userLocations) {
		this.userLocations = userLocations;
	}
	
	public UserLocation getLastKnownLocation() {
		UserLocation[] locations = (UserLocation[]) getUserLocations().toArray();
		
		if (locations.length > 0) 
			return (UserLocation) locations[0];
		else
			return null;
	}
	
	
	
}
