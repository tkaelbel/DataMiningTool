package com.tok.data.mining.algorithms.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AprioriInputModel extends AlgorithmModel{
	
	private Double minimumConfidence;
	private Double minimumSupport;
	private Integer itemCount;
	
}
