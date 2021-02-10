package com.tok.data.mining.algorithms;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.tok.data.mining.payload.request.AlgorithmRequest;
import com.tok.data.mining.payload.response.AlgorithmResponse;

@Component
public class AlgorithmManager {

	@Autowired
	private AlgorithmFactory algorithmFactory;
	
	
	public Set<String> getRegisteredAlgorithms(){
		Set<String> names = new HashSet<>();
		for(AlgorithmName name : AlgorithmName.values()) {
			names.add(name.toString());
		}
		return names;
	}
	
	public AlgorithmResponse executeAlgorithm(AlgorithmRequest request) {
		Assert.notNull(request, "request object is null");
		Assert.notNull(request.getName(), "algorithName is null");
		Assert.notNull(request.getProperties(), "properties is null");
		Assert.notNull(request.getDatabaseName(), "databaseName is null");
		Assert.notNull(request.getCollectionName(), "collectionName is null");
		
		IAlgorithm algorithm = algorithmFactory.getAlgorithm(request.getEnumName());
		if(algorithm != null) algorithm.execute(request);
		
		
		return null;
	}
	
}

