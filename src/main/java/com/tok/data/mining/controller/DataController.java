package com.tok.data.mining.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tok.data.mining.mongodb.MongoDbCentral;
import com.tok.data.mining.payload.request.CollectionDataRequest;
import com.tok.data.mining.payload.response.CollectionDataResponse;
import com.tok.data.mining.payload.response.DatabaseDataResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/data")
public class DataController {

	@Autowired
	private MongoDbCentral central;

	@GetMapping("/databases")
//	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<DatabaseDataResponse>> databases() {		
		return ResponseEntity.ok(central.getDatabases());
	}
	
	@GetMapping("/collectionData")
	public ResponseEntity<CollectionDataResponse> collectionData(CollectionDataRequest request) {
		return ResponseEntity.ok(central.getCollectionData(request.getDatabaseName(), request.getCollectionName()));
	}
}
