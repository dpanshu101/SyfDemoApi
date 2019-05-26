package com.demo.syf.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.syf.entities.User;
import com.demo.syf.model.UserPrincipal;
import com.demo.syf.repositories.UserRepo;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserPrincipal(user);
	}

	public boolean addNewUser(User user) {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
		return true;
	}
	
	public List<User> fetchAllUsers(){
		return (List<User>) userRepo.findAll();
	}

}
