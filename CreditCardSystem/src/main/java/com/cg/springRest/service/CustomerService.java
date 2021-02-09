package com.cg.springRest.service;

import com.cg.springRest.model.Customer;

public interface CustomerService {

	 Customer addCustomer(Customer customer);
	 
	 Customer getCustomerById(String email);
}