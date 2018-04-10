package com.foodbarbaz;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<FBBUser, Long> {
	
	
	List<FBBUser> findAllByIdNotLike(@Param("id") Long id);
	
	
}

