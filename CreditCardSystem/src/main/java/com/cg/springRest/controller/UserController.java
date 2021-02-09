package com.cg.springRest.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.springRest.exception.CustomerNotFoundException;
import com.cg.springRest.model.User;
import com.cg.springRest.repository.UserRepository;
import com.cg.springRest.service.UserService;

@RestController
@RequestMapping("/ccs")
public class UserController {

	@Autowired
	UserService userService;
	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;

	@CrossOrigin()
	@PostMapping("/users/login")
	public Status loginUser(@Valid @RequestBody User user) {
		List<User> users = userRepository.findAll();

		for (User other : users) {
			if (other.equals(user)) {
				user.setLoggedIn(true);
				return Status.SUCCESS;
			}
		}

		return Status.FAILURE;
	}

	@CrossOrigin()
	@PostMapping("/users/logout")
	public Status logUserOut(@Valid @RequestBody User user) {
		List<User> users = userRepository.findAll();

		for (User other : users) {
			if (other.equals(user)) {
				user.setLoggedIn(false);
				// userRepository.save(user);
				return Status.SUCCESS;
			}
		}

		return Status.FAILURE;
	}

	@PostMapping("/addAdmin")
	public User addAdmin(@RequestBody User user) {
		user.setUserRoleId(1);
		User userInfo = userService.addAdmin(user);
		return userInfo;
	}

	@GetMapping("/getAdmin/{id}")
	public User getAdmin(@PathVariable("id") int id) throws Exception {
		User admin = userService.getAdmin(id);
		if (admin.getUserRoleId() != 1 || admin.getUserRoleId() == 0 || admin == null) {
			logger.warn("No admin Present with the ID = " + id);
			throw new CustomerNotFoundException("No admin Present with the ID = " + id);
		} else
			return admin;
	}

	@DeleteMapping("/deleteAdmin/{id}")
	public ResponseEntity<Optional<User>> deleteAdmin(@PathVariable int id) {
		Optional<User> deletedAdmin;
		User user = (User) userService.getAdmin(id);
		if (user.getUserRoleId() != 1 || user == null) {
			logger.warn("No admin Present with the ID = " + id + " to delete");
			throw new CustomerNotFoundException("No admin Present with the ID = " + id + " to delete");
		} else {
			deletedAdmin = userService.deleteAdmin(id);
			return new ResponseEntity<Optional<User>>(deletedAdmin, HttpStatus.OK);
		}

	}

	@GetMapping("/getAllUsers")
	public ResponseEntity<List<User>> getUsers() throws Exception {
		List<User> admins = userService.getAllUsers();
		if (admins == null) {
			logger.warn("Not Customers found!");
			throw new CustomerNotFoundException("Not Customers found !");
		}
		return new ResponseEntity<List<User>>(admins, HttpStatus.OK);
	}

}