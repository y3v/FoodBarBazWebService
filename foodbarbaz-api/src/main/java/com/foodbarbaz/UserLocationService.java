package com.foodbarbaz;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserLocationService {
	
	//Connect to the database
	@Autowired
	private UserLocationRepository userLocationRepository;
	
	
	public void addUserLocation(UserLocation location) {
		((CrudRepository<UserLocation, Long>) userLocationRepository).save(location);
	}
	
	public List<UserLocation> getAllUserLocations(){
		List<UserLocation> locations = new ArrayList<UserLocation>();
		
		locations = (List<UserLocation>) ((CrudRepository<UserLocation, Long>) userLocationRepository).findAll();
		
		return locations;
	}

}
