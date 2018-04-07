/*package com.foodbarbaz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class FriendshipService {
	
	//Connect to the database
	@Autowired
	private FriendshipRepository friendshipRepository;
	
	
	public void addFriendship(Friendship friendship) {
		((CrudRepository<Friendship, Long>) friendshipRepository).save(friendship);
	}
	
	public List<Friendship> getAllFriendships(){
		List<Friendship> friends = new ArrayList<Friendship>();
		
		friends = (List<Friendship>) ((CrudRepository<Friendship, Long>) friendshipRepository).findAll();
		
		return friends;
	}
	
}
*/