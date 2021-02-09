package com.cg.springRest.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.springRest.exception.CustomerNotFoundException;
import com.cg.springRest.model.Customer;
import com.cg.springRest.service.CustomerServiceImplementation;



@RestController
@RequestMapping("/ccs")
public class CustomerController {

	@Autowired
	CustomerServiceImplementation customerService;
	Logger logger=LoggerFactory.getLogger(CustomerController.class);

	@PostMapping("/registerCustomer")
	public Customer addCustomer(@Valid @RequestBody Customer customer) {
		Customer customerInfo = customerService.addCustomer(customer);
		return customerInfo;
	}

	@GetMapping("/getAllCustomer/{email}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable String email) throws Exception {
		Customer getCustomer = customerService.getCustomerById(email);
		if (getCustomer == null) {
			logger.warn("Customer Not Found with given email! " +email);
			throw new CustomerNotFoundException("Customer Not Found with given email! " +email);
		}
		else
			return new ResponseEntity<Customer>(getCustomer, HttpStatus.OK);

	}
}