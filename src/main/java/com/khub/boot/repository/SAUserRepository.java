package com.khub.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khub.boot.model.SAUser;

@Repository
public interface SAUserRepository extends JpaRepository<SAUser, Integer>{
	
	public SAUser findByUsername(String userString);

}
