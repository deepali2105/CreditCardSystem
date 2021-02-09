package com.cg.springRest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.springRest.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

}