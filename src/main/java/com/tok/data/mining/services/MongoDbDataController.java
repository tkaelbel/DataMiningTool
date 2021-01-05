package com.tok.data.mining.services;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tok.data.mining.model.DataMiningUser;
import com.tok.data.mining.mongodb.MongoDbCentral;

@RestController
public class MongoDbDataController {

	@Autowired
	private MongoDbCentral central;
	
	
	@GetMapping("/databases")
	public List<String> all(){
		return List.copyOf(central.getDatabasesNames());
	}
	
	
	@GetMapping("/collections")
	public List<String> getCollections(){
		return null;
	}
	
	
	@GetMapping("/login")
	@ResponseBody
	public Principal login(Principal user) {
//		userService.
		System.out.println(user.getName());
		return null;
	}
	
	
	
}
