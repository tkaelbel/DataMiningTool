package com.tok.data.mining.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tok.data.mining.model.DataMiningRole;

@Repository
public interface DataMiningRoleRepository extends MongoRepository<DataMiningRole, String>{

	Optional<DataMiningRole> findByRoleName(String role);
	
}
