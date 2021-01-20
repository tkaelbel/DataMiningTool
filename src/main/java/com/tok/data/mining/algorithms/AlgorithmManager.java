package com.tok.data.mining.algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	
}

