package com.cg.springRest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.springRest.model.Customer;
import com.cg.springRest.repository.CustomerRepository;


@Service
@Transactional
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	CustomerRepository custRepository;

	public Customer addCustomer(Customer customer) {
		return custRepository.save(customer);
	}

	
	  public Customer getCustomerById(String email) { 
		  return custRepository.findById(email).orElse(null); }
	 
}