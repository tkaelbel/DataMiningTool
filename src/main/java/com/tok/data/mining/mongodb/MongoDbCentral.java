package com.tok.data.mining.mongodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.tok.data.mining.mongodb.model.CollectionColumnDataModel;
import com.tok.data.mining.payload.response.CollectionDataResponse;
import com.tok.data.mining.payload.response.DatabaseDataResponse;
import com.tok.data.mining.payload.response.KeyValue;

@Component
public class MongoDbCentral {

	private static List<String> BLACK_LIST_DATABASE_NAMES = Arrays.asList("data_mining_admin", "admin", "local",
			"config");

	@Autowired
	private MongoClient client;

	public List<DatabaseDataResponse> getDatabases() {
		List<DatabaseDataResponse> models = new ArrayList<>();

		client.listDatabaseNames().forEach(s -> {

			if (BLACK_LIST_DATABASE_NAMES.contains(s))
				return;

			DatabaseDataResponse model = new DatabaseDataResponse(s, null);
			MongoDatabase database = client.getDatabase(s);
			Set<String> collectionNames = new HashSet<>();
			database.listCollectionNames().forEach(c -> {
				collectionNames.add(c);
			});
			model.setCollections(collectionNames);
			models.add(model);
		});

		return models;
	}

	public CollectionDataResponse getCollectionData(String databaseName, String collectionName) {
		Assert.notNull(databaseName, "databaseName cannot be null");
		Assert.notNull(collectionName, " collecitonName cannot be null");

		List<List<KeyValue>> values = new ArrayList<>();
		Set<String> columns = new HashSet<>();
		CollectionDataResponse response = new CollectionDataResponse(databaseName, collectionName, columns, values);

		MongoDatabase database = client.getDatabase(databaseName);
		MongoCollection<Document> collection = database.getCollection(collectionName);

		FindIterable<Document> data = collection.find();
		data.forEach(t -> {
			List<KeyValue> formattedData = new ArrayList<>();
			t.entrySet().forEach(entry -> {
				columns.add(entry.getKey());

				formattedData.add(new KeyValue(entry.getKey(),
						entry.getValue() instanceof ObjectId ? entry.getValue().toString() : entry.getValue()));
			});
			values.add(formattedData);
		});

		return response;
	}

	
	public CollectionColumnDataModel getColumnCollectionData(String databaseName, String collectionName, String column) {
		Assert.notNull(databaseName, "databaseName cannot be null");
		Assert.notNull(collectionName, "collecitonName cannot be null");
		Assert.notNull(column, "column cannot be null");
		
		MongoDatabase database = client.getDatabase(databaseName);
		MongoCollection<Document> collection = database.getCollection(collectionName);
		
		List<Object> values = new ArrayList<>();
		CollectionColumnDataModel model = new CollectionColumnDataModel(column, values);
				
		FindIterable<Document> data = collection.find();
		
		data.forEach(t -> {
			t.entrySet().forEach(entry -> {
				if(entry.getKey().equals(column)) {
					values.add(entry.getValue());
				}
				
			});
		});
		
		return model;
	}
}
