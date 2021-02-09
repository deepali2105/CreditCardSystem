package com.cg.springRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.springRest.model.Transaction;



public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

	//public Transaction findByCreditCard(long creditCardNumber);
	
	public List<Transaction> findAllByCreditCardNumber(long creditCardNumber);
}
