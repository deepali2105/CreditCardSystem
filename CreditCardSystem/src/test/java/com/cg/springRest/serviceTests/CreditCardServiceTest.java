package com.cg.springRest.serviceTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.springRest.model.CreditCard;
import com.cg.springRest.repository.CreditCardRepository;
import com.cg.springRest.service.CreditCardService;
import com.cg.springRest.service.CreditCardServiceImplementation;

@SpringBootTest
public class CreditCardServiceTest {

	@Autowired
	CreditCardServiceImplementation service;

	@MockBean
	CreditCardRepository repository;

	CreditCardService listMock = mock(CreditCardService.class, "myMock");

	@Test
	public void buyCreditCardTest() {
		CreditCard creditCard = new CreditCard(45000, "Gold Card", "2021-02-05", "2031-02-05", 45000, 39);
		when(repository.save(creditCard)).thenReturn(creditCard);
		assertEquals(creditCard, service.buyCreditCard(45000, creditCard));
	}

	@Test
	public void getCreditDetailsById() {
		long creditNo = 253;
		List<CreditCard> listCreditCard = Stream
				.of(new CreditCard(45000, "Gold Card", "2021-02-05", "2031-02-05", 45000, 39),
						new CreditCard(35000, "Silver Card", "2021-03-05", "2031-03-05", 35000, 89))
				.collect(Collectors.toList());
		repository.saveAll(listCreditCard);
		when(repository.findById(creditNo)).thenReturn(Optional.of(listCreditCard.get(0)));
		assertEquals(listCreditCard.get(0), service.getCreditDetails(creditNo));
	}
}
