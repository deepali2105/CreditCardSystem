package com.cg.springRest.service;

import java.util.List;

import com.cg.springRest.model.Transaction;



public interface TransactionService {

	List<Transaction> getTransactionDetails(long creditCardNumber);

}