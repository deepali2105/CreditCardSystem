package com.cg.springRest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.springRest.model.Transaction;
import com.cg.springRest.repository.TransactionRepository;



@Service
@Transactional
public class TransactionServiceImplementation implements TransactionService {

	@Autowired
	TransactionRepository transRepository;

	public List<Transaction> getTransactionDetails(long creditCardNumber) {
		return transRepository.findAllByCreditCardNumber(creditCardNumber);
	}

}