package com.tok.data.mining.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class DataMiningUser {

	@Id
	private String _id;
	@Field
	private String userName;
	@Field
	private String email;
	@Field
	private String password;
	@Field
	private String name;
	@Field
	private String lastName;
	@Field
	private Boolean isActive;
	@DBRef
	private Set<DataMiningRole> roles;
	
	
	public DataMiningUser(String userName, String email, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}
	
	
	
	
}
