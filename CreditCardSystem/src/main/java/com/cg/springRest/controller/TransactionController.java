package com.cg.springRest.controller;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.springRest.exception.CustomerNotFoundException;
import com.cg.springRest.model.CreditCard;
import com.cg.springRest.model.Transaction;
import com.cg.springRest.repository.CreditCardRepository;
import com.cg.springRest.repository.TransactionRepository;
import com.cg.springRest.service.TransactionService;
import com.cg.springRest.util.PDFGenerator;

@RestController
@RequestMapping("/ccs")
public class TransactionController {

	@Autowired
	TransactionService transService;
	Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	CreditCardRepository creditRepository;

	@Autowired
	TransactionRepository transRepository;
	
	@GetMapping(value = "/getTransactionDetails/{creditCardNumber}", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> getTransactionDetails(@PathVariable long creditCardNumber) {
		List<Transaction> listOfTransactions = transService.getTransactionDetails(creditCardNumber);
		if (listOfTransactions == null) {
			logger.warn("No Transaction Present");
			throw new CustomerNotFoundException("No Transaction Present");
		} else {
			ByteArrayInputStream bis = PDFGenerator.customerPDFReport(listOfTransactions);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=transactions.pdf");

			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));

		}

	}

	@PostMapping("/makePayment/{amount}/{creditCardNumber}")
	public Transaction makePayment(@PathVariable long amount, @PathVariable long creditCardNumber) throws Exception {
		Transaction transaction = new Transaction();
		boolean ifExists = creditRepository.existsById(creditCardNumber);
		if (ifExists) {
			CreditCard card = creditRepository.findByCreditcardNo(creditCardNumber);
			double availableBalance = card.getAvailableBalance();
			if (amount < availableBalance) {
				transaction.setcreditCardNumber(creditCardNumber);
				transaction.setPaymentMethod("Credit Card");
				transaction.setTransValue(amount);
				transaction.setTransDate(new Date());
				availableBalance = availableBalance - amount;
				card.setAvailableBalance((long) availableBalance);
				creditRepository.save(card);
				transRepository.save(transaction);
				return transaction;
			} else {
				logger.warn("Not Available Balance");
				throw new CustomerNotFoundException("Not Available Balance");
			}
		} else {
			logger.warn("Credit Card Invalid");
			throw new CustomerNotFoundException("Credit Card Invalid");
		}
	}
}