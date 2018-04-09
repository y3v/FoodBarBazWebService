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
	/*
	 * @Autowired private FriendshipRepository friendshipRepository;
	 */

	// Get all users from the database - WORKS
	public List<FBBUser> getAllUsers() {
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
	
	/*public List<UserLocation> getFriendsLocation(Long userId){
		List<UserLocation> friendsLocation = new ArrayList<>();
		Set<FBBUser> friends = userRepository.findOne(userId).getFriends();
		
		for (FBBUser user : friends) {
			friendsLocation.add(user.getLastKnownLocation());
		}
		
		return friendsLocation;
	}*/
	
	public void deleteAllUserLocation(Long userId) {
		FBBUser user = userRepository.findOne(userId);
		((CrudRepository<UserLocation, Long>) userLocationRepository).delete(user.getUserLocations());
	}

}
