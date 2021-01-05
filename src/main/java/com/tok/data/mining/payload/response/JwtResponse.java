package com.tok.data.mining.payload.response;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private String id;
	private String username;
	private String email;
	private List<String> roles;

	public JwtResponse(String token, String id, String username, String email, List<String> roles) {
		super();
		this.token = token;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
}
