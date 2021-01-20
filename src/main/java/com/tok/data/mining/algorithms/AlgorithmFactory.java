package com.tok.data.mining.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class AlgorithmFactory {

	private Map<AlgorithmName, IAlgorithm> createdAlgorithms;
	
	public AlgorithmFactory(Set<IAlgorithm> algorithms) {
		createAlgorithms(algorithms);
	}

	private void createAlgorithms(Set<IAlgorithm> algorithms) {
		createdAlgorithms = new HashMap<>();
		algorithms.forEach(a -> {
			createdAlgorithms.put(a.getName(), a);
		});
	}
	
	public IAlgorithm getAlgorithm(AlgorithmName name) {
		return createdAlgorithms.get(name);
	}
	
}
