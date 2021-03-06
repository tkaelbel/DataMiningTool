package com.tok.data.mining.algorithms;

import com.tok.data.mining.algorithms.models.AlgorithmModel;
import com.tok.data.mining.payload.request.AlgorithmRequest;

public interface IAlgorithm {
	
	public AlgorithmReturnModel execute(AlgorithmRequest data);

	public AlgorithmName getName();
	
	public AlgorithmModel getModel(Object inputProperties);
	
}

