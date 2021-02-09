package com.cg.springRest.service;

import java.util.List;
import java.util.Optional;

import com.cg.springRest.model.User;



public interface UserService {

	User getAdmin(int id);
	
	List<User> getAllUsers();

	User addAdmin(User user);
	
	Optional<User> deleteAdmin(int id);

	User registerUser(User user);
}