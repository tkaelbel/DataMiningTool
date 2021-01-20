package com.tok.data.mining.payload.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlgorithmNamesResponse {

	private Set<String> algorithmNames;
}
