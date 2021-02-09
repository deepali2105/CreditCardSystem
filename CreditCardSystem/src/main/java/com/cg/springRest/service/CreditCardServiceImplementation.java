package com.cg.springRest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.springRest.model.CreditCard;
import com.cg.springRest.repository.CreditCardRepository;
import com.cg.springRest.repository.UserRepository;



@Service
@Transactional

public class CreditCardServiceImplementation implements CreditCardService {

	@Autowired
	CreditCardRepository creditRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public CreditCard getCreditDetails(long creditNo) {
		return creditRepository.findById(creditNo).orElse(null);
	}

	@Override
	public CreditCard buyCreditCard(double salary, CreditCard creditCard) {
		return creditRepository.save(creditCard);
	}

	

}