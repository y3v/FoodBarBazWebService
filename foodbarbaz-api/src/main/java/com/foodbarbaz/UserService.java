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

	// Connect to the database
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserLocationRepository userLocationRepository;
	
	@Autowired
	private FollowingRepository followingRepository;
	/*
	 * @Autowired private FriendshipRepository friendshipRepository;
	 */

	// Get all users from the database - WORKS
	public List<FBBUser> getAllUsers() {
		System.out.println("GETTING ALL USERS");
		List<FBBUser> users = new ArrayList<>();
		users = (List<FBBUser>) ((CrudRepository<FBBUser, Long>) userRepository).findAll();

		return users;
	}

	// Add user - WORKS
	public void addUser(FBBUser user) {
		((CrudRepository<FBBUser, Long>) userRepository).save(user);
	}

	// Get one user and return it
	public FBBUser getUser(LoginHolder loginHolder) {
		List<FBBUser> users = new ArrayList<>();
		users = (List<FBBUser>) ((CrudRepository<FBBUser, Long>) userRepository).findAll();

		for (FBBUser fbbUser : users) {
			System.out.println("Username: " + fbbUser.getUsername() + " Password: " + fbbUser.getPassword());
			if (fbbUser.getPassword().equals(loginHolder.getPassword())
					&& fbbUser.getUsername().equals(loginHolder.getUsername())) {
				System.out.println("FOUND A MATCH!!!");
				return fbbUser;
			}
		}
		return null;
	}

	public List<FBBUser> getAllNotFollowing(Long id) {
		List<FBBUser> users = new ArrayList<>();

		// get all users that are not the user asking
		users = userRepository.findAllByIdNotLike(id);
		// get the calling user
		FBBUser user = userRepository.findOne(id);
		// get existing friends
		Set<Following> friends = user.getFriends();
		
		Iterator<FBBUser> iter = users.iterator();

		while (iter.hasNext()) {
		    FBBUser u = iter.next();

		    for (Following f : friends) {
				if (u.getId() == f.getFriendId()) {
					iter.remove();
				}
			}
		}

		return users;
	}

	public void addFriendship(Long requesterId, Long friendId) {
		FBBUser requester = userRepository.findOne(requesterId);

		requester = userRepository.findOne(requesterId);
		
		Following following = new Following();
		following.setFollower(requester);
		following.setFriendId(friendId);

		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		requester.getFriends().add(following);
		
		// ((CrudRepository<Friendship, Long>) friendshipRepository).save(fs);
		((CrudRepository<FBBUser, Long>) userRepository).save(requester);
	}
	
	public void removeFriendship(Long requesterId, Long friendId) {
		System.out.println("REMOVING USER");
		FBBUser requester = userRepository.findOne(requesterId);
		System.out.println("REQUESTER:: " + requester.getFirstname() + " " + requester.getId());

		requester = userRepository.findOne(requesterId);
		
		Set<Following> following = requester.getFriends();
		for (Following fl : following) {
			System.out.println("FRIEND:: " + fl.getFriendId());
			System.out.println("VS:: " + friendId);
		}
		
		Iterator<Following> iter = following.iterator();
		while (iter.hasNext()) {
		    Following f = iter.next();
		    System.out.println("Following f::: " + f.getFriendId());
		    if (f.getFriendId() == friendId) {
		    	System.out.println("MATCH::: REMOVING FRIENDSHIP ::::");
		    	iter.remove();
		    }
		}
		
		for (Following following2 : following) {
			System.out.println("AFTER REMOVAL:" + following2.getFriendId());
		}

		requester.setFriends(following);
		
		// ((CrudRepository<Friendship, Long>) friendshipRepository).save(fs);
		((CrudRepository<FBBUser, Long>) userRepository).save(requester);
		
	}

	public List<FBBUser> getAllFriends(Long id) {
		List<FBBUser> friends = new ArrayList<FBBUser>();
		FBBUser requester = userRepository.findOne(id);

		Set<Following> rawList = requester.getFriends();
		
		for (Following following : rawList) {
			System.out.println("Friend ID: " + following.getFriendId());
			friends.add(userRepository.findOne(following.getFriendId()));
		}

		return friends;
	}

	public void addUserLocation(UserLocation userLocation) {
		if (userLocation == null)
			return;

		FBBUser user = userRepository.findOne(userLocation.getUser().getId());
		user.getUserLocations().add(userLocation);
		userLocation.setUser(user);

		((CrudRepository<FBBUser, Long>) userRepository).save(user);
	}

	public Set<UserLocation> getLastUserLocation(Long userId, int amount) {
		Set<UserLocation> locationSet = userRepository.findOne(userId).getUserLocations();
		Object[] locations = locationSet.toArray();
		locationSet.removeAll(locationSet);

		int count = amount > locations.length ? locations.length : amount;
		
		for (int i = 0; i < count; i++) {
			locationSet.add((UserLocation)locations[i]);
			System.out.println(locations[i].toString());
			System.out.println("locations type " + ((UserLocation)locations[i]).getClass());
		}
		System.out.println("locations count " + locations.length);
		return locationSet;
	}
	
	public List<UserLocation> getFriendsLocation(Long userId){
		List<UserLocation> friendsLocation = new ArrayList<>();
		Set<Following> followers = userRepository.findOne(userId).getFriends();
		
		for (Following follower : followers) {
			UserLocation[] locations = (UserLocation[]) follower.getFollower().getUserLocations().toArray();
			
			if (locations.length > 0) 
				friendsLocation.add((UserLocation) locations[0]);
		}
		
		return friendsLocation;
	}
	
	public UserLocation getFriendLocation(Long userId) {
		FBBUser user = userRepository.findOne(userId);
		Set<UserLocation> locs = user.getUserLocations();
		
		for (UserLocation userLocation : locs) {
			System.out.println(userLocation.getLatitude());
		}
		Object[] locations = locs.toArray();
		
		if (locations != null && locations.length > 0) {
			Object lastKnownLocation = locations[0];
			UserLocation location = (UserLocation) lastKnownLocation;
			return location;
		}
		else
			return null;
	}
	
	public void deleteAllUserLocation(Long userId) {
		FBBUser user = userRepository.findOne(userId);
		((CrudRepository<UserLocation, Long>) userLocationRepository).delete(user.getUserLocations());
	}

}
