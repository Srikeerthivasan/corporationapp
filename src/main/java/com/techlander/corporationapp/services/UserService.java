package com.techlander.corporationapp.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techlander.corporationapp.models.User;
import com.techlander.corporationapp.repositories.UserRepository;

@Service
public class UserService {

	@Autowired private UserRepository userRepository;
	@Autowired private BCryptPasswordEncoder encoder;
	
	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
	}

	public User findById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public User findByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}
}
