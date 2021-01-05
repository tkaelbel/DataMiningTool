package com.tok.data.mining.mongodb;

import java.util.HashSet;
import java.util.Set;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.tok.data.mining.model.DataMiningUser;

@Component
public class MongoDbCentral {
		
	private static final String ADMIN_DB = "data_mining_admin";
	private static final String USER = "user";
	private static final String ROLE = "role";
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
	
	public DataMiningUser save(DataMiningUser user) {
		MongoCollection<Document> userCollection = getUserCollection();
		ObjectMapper mapper = new ObjectMapper();
		
//		userCollection.insertOne();
		
		return null;
	}
	
	public DataMiningUser findUserByName(String name) {
		MongoCollection<Document> userCollection = getUserCollection();
		FindIterable<Document> foundDocument = userCollection.find(Filters.eq("userName", name));
		Document documentUser = foundDocument.first();
		ObjectMapper mapper = new ObjectMapper();
		try {
			DataMiningUser user = mapper.readValue(documentUser.toJson(), DataMiningUser.class);
			return user;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private MongoCollection<Document> getUserCollection(){
		MongoDatabase database = client.getDatabase(MongoDbCentral.ADMIN_DB);
		MongoCollection<Document> collection = database.getCollection(MongoDbCentral.USER);
		return collection;
	}
	
	
	
	
	
	
	
	
	
	

}
