package com.tok.data.mining.payload.request;

import java.util.Set;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

	@NonNull
	private String username;

	@NonNull
	private String email;

	private Set<String> roles;

	@NonNull
	private String password;

}
