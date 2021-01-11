package com.tok.data.mining.payload.response;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDataResponse {
	
	private String databaseName;
	private String collectionName;
	private Set<String> columns;
	private List<List<KeyValue>> data;
}
