package com.foodbarbaz;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUsers")
	public List<FBBUser> getUsers(){
		return userService.getAllUsers();
	}
	
	@RequestMapping("/getFollowing/{id}")
	public List<FBBUser> getFollowing(@PathVariable Long id){
		return userService.getAllFollowing(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/register")
	public void registerUser(@RequestBody FBBUser user){
		userService.addUser(user);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/login")
	public FBBUser login(@RequestBody LoginHolder loginHolder) {
		return userService.getUser(loginHolder);
	}
	
	@RequestMapping("/addFriendship/{idReq}/{idFriend}")
	public void addFriendship(@PathVariable Long idReq, @PathVariable Long idFriend){
		userService.addFriendship(idReq,idFriend);
	}
	
	@RequestMapping("/getFriends/{id}")
	public Set<FBBUser> getFriends(@PathVariable Long id){
		return userService.getAllFriends(id);
	}
	
	@RequestMapping("/addUserLocation/{userId}/{latlon}")
	public void addUserLocation(@PathVariable long userId, @PathVariable String latlon) {
		userService.addUserLocation(userId, latlon);
	}
	
	@RequestMapping("/getLastUserLocation/{userId}/{count}")
	public Set<UserLocation> getLastUserLocation(@PathVariable Long userId, @PathVariable int count){
		return userService.getLastUserLocation(userId, count);
	}
	
	@RequestMapping("/deleteAllUserLocation/{userId}")
	public void deleteAllUserLocation(@PathVariable long userId) {
		userService.deleteAllUserLocation(userId);
	}
}
