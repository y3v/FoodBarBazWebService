package com.foodbarbaz;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	//Connect to the database
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserLocationRepository userLocationRepository;
	/*@Autowired
	private FriendshipRepository friendshipRepository;*/
	
	
	//Get all users from the database - WORKS
	public List<FBBUser> getAllUsers(){
		List<FBBUser> users = new ArrayList<>();
		users = (List<FBBUser>) ((CrudRepository<FBBUser, Long>) userRepository).findAll();
		
		return users;
	}
	
	//Add user - WORKS
	public void addUser(FBBUser user) {
		((CrudRepository<FBBUser, Long>) userRepository).save(user);
	}
	
	//Get one user and return it
	public FBBUser getUser(LoginHolder loginHolder) {
		List<FBBUser> users = new ArrayList<>();
		users = (List<FBBUser>) ((CrudRepository<FBBUser, Long>) userRepository).findAll();
		
		for (FBBUser fbbUser : users) {
			System.out.println("Username: " + fbbUser.getUsername() + " Password: " + fbbUser.getPassword());
			if (fbbUser.getPassword().equals(loginHolder.getPassword()) && fbbUser.getUsername().equals(loginHolder.getUsername())) {
				System.out.println("FOUND A MATCH!!!");
				return fbbUser;
			}
		}
		return null;
	}
	
	public List<FBBUser> getAllFollowing(Long id){
		List<FBBUser> users = new ArrayList<>();
		
		//get all users that are not the user asking
		users = userRepository.findAllByIdNotLike(id);
		//get the calling user 
		FBBUser user = userRepository.findOne(id);
		//get existing friends
		Set<FBBUser> friends = user.getFriends();
		
		return users;
	}
	
	public void addFriendship(Long requesterId, Long friendId) {
		FBBUser requester;
		FBBUser friend;
		
		requester = userRepository.findOne(requesterId);
		friend = userRepository.findOne(friendId);
		
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		
		requester.getFriends().add(friend);
		
		for (FBBUser f : requester.getFriends()) {
			System.out.println(f.getId() + " " + f.getFirstname());
			System.out.println(f.getId() + " "	+ f.getFirstname());
		}
		
		System.out.println("PRE-REQUESTER ID:" + requesterId);
		System.out.println("PRE-FRIEND ID:" + friendId);
		System.out.println("DATE:" + date);
		
		System.out.println("REQUESTER ID:" + requester.getId() + " NAME: " + requester.getFirstname());
		System.out.println("FRIEND ID:" + friend.getId() + " NAME: " + friend.getFirstname());
		
		//((CrudRepository<Friendship, Long>) friendshipRepository).save(fs);
		((CrudRepository<FBBUser, Long>) userRepository).save(requester);
	}
	
	public Set<FBBUser> getAllFriends(Long id){
		Set<FBBUser> friends = new HashSet<FBBUser>();
		
		FBBUser user = userRepository.findOne(id);
		
		friends = user.getFriends();
		
		return friends;
	}
	
	public void addUserLocation(Long userId, String location) {
		System.out.println("" + userId + ", " + location);
		if (location == null || location.isEmpty())
			return;
		
		FBBUser user = userRepository.findOne(userId);
		UserLocation userLocation;
		
		double lat, lon;
		String[] latlon = location.split(":");
		
		lat = Double.parseDouble(latlon[0]);
		lon = Double.parseDouble(latlon[1]);
		
		userLocation = new UserLocation(lat, lon);
		userLocation.setUser(user);
		
		((CrudRepository<UserLocation, Long>) userLocationRepository).save(userLocation);
	}
	
	public Set<UserLocation> getLastUserLocation(Long userId, int amount){
		Set<UserLocation> locationSet = userRepository.findOne(userId).getUserLocations();
		UserLocation[] locations = (UserLocation[]) locationSet.toArray();
		locationSet.removeAll(locationSet);
		
		int count = amount > locations.length ? locations.length : amount;
		
		for (int i = 0; i < count; i++) 
			locationSet.add(locations[i]);
		
		return locationSet;
	}
	
}
