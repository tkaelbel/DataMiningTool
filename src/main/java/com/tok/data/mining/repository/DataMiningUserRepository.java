package com.tok.data.mining.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tok.data.mining.model.DataMiningUser;

@Repository
public interface DataMiningUserRepository extends MongoRepository<DataMiningUser, String>{

	Optional<DataMiningUser> findByUserName(String userName);
	
	Boolean existsByUserName(String userName);
	
	Boolean existsByEmail(String email);
}
