/*package com.foodbarbaz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendshipController {
	
	@Autowired
	FriendshipService friendshipService;
	
	@RequestMapping(method=RequestMethod.POST, value="/addFriendship")
	public void addFriendship(@RequestBody Friendship friendship){
		friendshipService.addFriendship(friendship);
	}
	
	@RequestMapping("/getFriends")
	public List<Friendship> getAllFriendships(){
		return friendshipService.getAllFriendships();
	}

}
*/