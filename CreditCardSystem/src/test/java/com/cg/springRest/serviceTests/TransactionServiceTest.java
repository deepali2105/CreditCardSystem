package com.cg.springRest.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.springRest.model.Transaction;
import com.cg.springRest.repository.TransactionRepository;
import com.cg.springRest.service.TransactionService;
import com.cg.springRest.service.TransactionServiceImplementation;

@SpringBootTest
public class TransactionServiceTest {

	@Autowired
	TransactionServiceImplementation service;

	@MockBean
	TransactionRepository repository;

	TransactionService listMock = mock(TransactionService.class, "myMock");

	@SuppressWarnings("unchecked")
	@Test
	public void getTransactionDetailsTest() {
		long creditNo = 253;
		List<Transaction> listTransaction = Stream.of(new Transaction("Credit Card", 35000, new Date(), 252),
				new Transaction("Credit Card", 20000, new Date(), 243)).collect(Collectors.toList());
		repository.saveAll(listTransaction);
		when(repository.findAllByCreditCardNumber(creditNo)).thenReturn((List<Transaction>) listTransaction.get(0));
		assertEquals(listTransaction.get(0), service.getTransactionDetails(creditNo));
	}

}