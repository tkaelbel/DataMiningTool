package com.tok.data.mining.payload.request;

import com.tok.data.mining.algorithms.AlgorithmName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlgorithmRequest {
	
	private String name;
	private Object properties;
	private String databaseName;
	private String collectionName;
	private String columnName;
	
	
	public AlgorithmName getEnumName() {
		return AlgorithmName.valueOf(this.name.toUpperCase());
	}
}
