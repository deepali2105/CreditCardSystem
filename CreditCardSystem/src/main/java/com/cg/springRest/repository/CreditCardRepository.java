package com.cg.springRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.springRest.model.CreditCard;



@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

	CreditCard findByCreditcardNo(long creditCardNumber);

}