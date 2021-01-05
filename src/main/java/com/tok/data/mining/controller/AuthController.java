package com.tok.data.mining.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tok.data.mining.model.DataMiningRole;
import com.tok.data.mining.model.DataMiningUser;
import com.tok.data.mining.payload.request.LoginRequest;
import com.tok.data.mining.payload.request.SignupRequest;
import com.tok.data.mining.payload.response.JwtResponse;
import com.tok.data.mining.payload.response.MessageResponse;
import com.tok.data.mining.repository.DataMiningRoleRepository;
import com.tok.data.mining.repository.DataMiningUserRepository;
import com.tok.data.mining.security.jwt.JwtUtil;
import com.tok.data.mining.security.services.UserDetailsModel;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private DataMiningUserRepository dataMiningUserRepository;

	@Autowired
	private DataMiningRoleRepository dataMiningRoleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtil.generateToken(authentication);

		UserDetailsModel userDetails = (UserDetailsModel) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> registerUser(@RequestBody SignupRequest signupRequest){
		
		if(dataMiningUserRepository.existsByUserName(signupRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: User name is already taken!"));
		}
		
		if(dataMiningUserRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email already in use!"));
		}
		
		DataMiningUser user = new DataMiningUser(signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()));
		
		Set<String> strRoles = signupRequest.getRoles();
		Set<DataMiningRole> roles = new HashSet<>();

		if (strRoles == null) {
			DataMiningRole userRole = dataMiningRoleRepository.findByRoleName("USER")
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			//maybe roles
		}

		user.setRoles(roles);
		dataMiningUserRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

}
