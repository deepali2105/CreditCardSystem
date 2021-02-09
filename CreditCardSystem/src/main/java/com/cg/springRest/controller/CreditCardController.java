package com.cg.springRest.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cg.springRest.exception.CustomerNotFoundException;
import com.cg.springRest.model.CreditCard;
import com.cg.springRest.repository.CreditCardRepository;
import com.cg.springRest.repository.UserRepository;
import com.cg.springRest.service.CreditCardService;

@Repository
@RequestMapping("/ccs")
public class CreditCardController {

	@Autowired
	CreditCardService creditService;
	Logger logger = LoggerFactory.getLogger(CreditCardController.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	CreditCardRepository credRepository;

	@GetMapping("/getCreditCardDetails/{creditNo}")
	public ResponseEntity<CreditCard> getCreditDetails(@PathVariable long creditNo) throws Exception {
		CreditCard getCreditCard = creditService.getCreditDetails(creditNo);
		if (getCreditCard == null) {
			logger.warn("Credit Card not Found ");
			throw new CustomerNotFoundException("Credit Card not Found ");
		}
		return new ResponseEntity<CreditCard>(getCreditCard, HttpStatus.OK);
	}

	@PostMapping("/buyCreditCard/{salary}/{id}")
	public ResponseEntity<CreditCard> buyCreditCard(@PathVariable double salary, @PathVariable int id,
			@RequestBody CreditCard creditCard) {

		boolean ifExists = userRepository.existsById(id);
		if (ifExists) {
			if (salary >= 25000 && salary < 40000) {
				CreditCard silverCard = creditService.buyCreditCard(salary, creditCard);
				silverCard.setCardType("Silver Card");
				silverCard.setCreditcardLimit(35000);
				silverCard.setAvailableBalance(35000);
				LocalDate vF = LocalDate.now();
				silverCard.setValidFrom(vF.toString());
				silverCard.setValidTo(vF.plusYears(10).toString());
				silverCard.setCustomerId(id);
				credRepository.save(silverCard);
				return new ResponseEntity<CreditCard>(silverCard, HttpStatus.OK);
			}

			else if (salary >= 40000 && salary < 60000) {
				CreditCard goldCard = creditService.buyCreditCard(salary, creditCard);
				goldCard.setCardType("Gold Card");
				goldCard.setCreditcardLimit(45000);
				goldCard.setAvailableBalance(45000);
				LocalDate vF = LocalDate.now();
				goldCard.setValidFrom(vF.toString());
				goldCard.setValidTo(vF.plusYears(10).toString());
				goldCard.setCustomerId(id);
				credRepository.save(goldCard);
				return new ResponseEntity<CreditCard>(goldCard, HttpStatus.OK);
			}

			else if (salary >= 60000 && salary < 80000) {
				CreditCard platinumCard = creditService.buyCreditCard(salary, creditCard);
				platinumCard.setCardType("Platinum Card");
				platinumCard.setCreditcardLimit(60000);
				platinumCard.setAvailableBalance(60000);
				LocalDate vF = LocalDate.now();
				platinumCard.setValidFrom(vF.toString());
				platinumCard.setValidTo(vF.plusYears(10).toString());
				platinumCard.setCustomerId(id);
				credRepository.save(platinumCard);
				return new ResponseEntity<CreditCard>(platinumCard, HttpStatus.OK);
			}

			else if (salary >= 80000) {
				CreditCard blackCard = creditService.buyCreditCard(salary, creditCard);
				blackCard.setCardType("Black Card");
				blackCard.setCreditcardLimit(75000);
				blackCard.setAvailableBalance(75000);
				LocalDate vF = LocalDate.now();
				blackCard.setValidFrom(vF.toString());
				blackCard.setValidTo(vF.plusYears(10).toString());
				blackCard.setCustomerId(id);
				credRepository.save(blackCard);
				return new ResponseEntity<CreditCard>(blackCard, HttpStatus.OK);
			} else {
				logger.warn("No Credit Card");
				throw new CustomerNotFoundException("No Credit Card");
			}
		}
		return new ResponseEntity<CreditCard>(creditCard, HttpStatus.BAD_REQUEST);
	}
}