package com.tok.data.mining.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tok.data.mining.model.DataMiningUser;
import com.tok.data.mining.repository.DataMiningUserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private DataMiningUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		DataMiningUser user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("User not found with user name: " + username));
		return UserDetailsModel.build(user);
	}

}
