package com.cg.springRest.service;

import com.cg.springRest.model.CreditCard;

public interface CreditCardService {

	CreditCard getCreditDetails(long creditNo);

	CreditCard buyCreditCard(double salary, CreditCard creditCard);

	

}