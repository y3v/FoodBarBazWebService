package com.foodbarbaz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	//Connect to the database
	@Autowired
	private UserRepository userRepository;
	
	
	//Get all users from the database - WORKS
	public List<FBBUser> getAllUsers(){
		List<FBBUser> users = new ArrayList<>();
		users = (List<FBBUser>) ((CrudRepository<FBBUser, String>) userRepository).findAll();
		
		return users;
	}
	
	//Add user - WORKS
	public void addUser(FBBUser user) {
		((CrudRepository<FBBUser, String>) userRepository).save(user);
	}
	
	//Get one user and return it
	public FBBUser getUser(LoginHolder loginHolder) {
		List<FBBUser> users = new ArrayList<>();
		users = (List<FBBUser>) ((CrudRepository<FBBUser, String>) userRepository).findAll();
		
		for (FBBUser fbbUser : users) {
			System.out.println("Username: " + fbbUser.getUsername() + " Password: " + fbbUser.getPassword());
			if (fbbUser.getPassword().equals(loginHolder.getPassword()) && fbbUser.getUsername().equals(loginHolder.getUsername())) {
				System.out.println("FOUND A MATCH!!!");
				return fbbUser;
			}
		}
		return null;
	
	}
	
}
