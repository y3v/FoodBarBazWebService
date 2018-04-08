package com.foodbarbaz;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;;

public interface UserLocationRepository extends CrudRepository<UserLocation, Long>{

}
