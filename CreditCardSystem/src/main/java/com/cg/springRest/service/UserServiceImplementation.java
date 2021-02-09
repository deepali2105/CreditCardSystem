package com.cg.springRest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.springRest.model.User;
import com.cg.springRest.repository.UserRepository;



@Service
@Transactional
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository repository;

	public User addAdmin(User user) {
		return repository.save(user);
	}

	public User getAdmin(int id) {
		return repository.findById(id).orElse(null);
	}

	public List<User> getAllUsers() {
		return repository.findAll();
	}

	public Optional<User> deleteAdmin(int id) {
		repository.deleteById(id);
		return repository.findById(id);
	}

	public User registerUser(User user) {
		return null;
	}

}