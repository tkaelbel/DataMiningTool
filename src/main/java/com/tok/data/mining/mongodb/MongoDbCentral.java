package com.tok.data.mining.mongodb;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.client.MongoClient;

@Component
public class MongoDbCentral {
		
	@Autowired
	private MongoClient client;
	
	public Set<String> getDatabasesNames(){
		Set<String> names = new HashSet<>();
		client.listDatabaseNames().forEach(names::add);
		return names;
	}
	
	
	public Set<String> getCollectionNames(){
		return null;
	}
	
	
	public void createCollection(String name) {
		
	}
	
	
	
	
	
	
	

}
