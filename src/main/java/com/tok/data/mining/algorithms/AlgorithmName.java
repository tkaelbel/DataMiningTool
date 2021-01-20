package com.tok.data.mining.algorithms;

public enum AlgorithmName {

	APRIORI, K_NEAREST, K_MEANS;

	@Override
	public String toString() {
		switch (this) {
		case APRIORI:
			return "apriori";
		case K_NEAREST:
			return "knearest";
		case K_MEANS:
			return "kmeans";
		default:
			throw new IllegalArgumentException();
		}
	}

}
