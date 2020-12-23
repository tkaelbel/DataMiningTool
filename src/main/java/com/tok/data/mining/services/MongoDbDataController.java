package com.tok.data.mining.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tok.data.mining.mongodb.MongoDbCentral;

@RestController
public class MongoDbDataController {

	@Autowired
	private MongoDbCentral central;
	
	
	@GetMapping("/databases")
	public List<String> all(){
		//Test
		return List.copyOf(central.getDatabasesNames());
	}
	
	
	
}
