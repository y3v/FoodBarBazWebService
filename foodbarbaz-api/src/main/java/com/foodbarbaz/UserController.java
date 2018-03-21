package com.foodbarbaz;

import java.util.List;

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
	
	@RequestMapping(method=RequestMethod.POST, value="/register")
	public void registerUser(@RequestBody FBBUser user){
		userService.addUser(user);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/login")
	public FBBUser login(@RequestBody LoginHolder loginHolder) {
		return userService.getUser(loginHolder);
	}
	
	
}
