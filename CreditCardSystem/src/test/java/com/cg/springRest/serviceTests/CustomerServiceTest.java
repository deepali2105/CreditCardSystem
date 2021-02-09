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

import com.cg.springRest.model.Customer;
import com.cg.springRest.model.User;
import com.cg.springRest.repository.CustomerRepository;
import com.cg.springRest.service.CustomerService;
import com.cg.springRest.service.CustomerServiceImplementation;

@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	CustomerServiceImplementation service;

	@MockBean
	CustomerRepository repository;

	CustomerService listMock = mock(CustomerService.class, "myMock");

	@Test
	public void addCustomerTest() {
		User user = new User(2, "adkale", "aditya", 2);
		Customer cust = new Customer("Aditya Kale", "9834938517", "adi19@gmail.com", user);
		when(repository.save(cust)).thenReturn(cust);
		assertEquals(cust, service.addCustomer(cust));
	}

	@Test
	public void getCustomerById() {
		String Email = "adi19@gmail.com";
		User user = new User(2, "adkale", "aditya", 2);
		List<Customer> listCustomer = Stream.of(new Customer("Aditya Kale", "9834938517", "adi19@gmail.com", user),
				new Customer("Anu Kale", "9422950999", "anu21@gmail.com", user)).collect(Collectors.toList());
		repository.saveAll(listCustomer);
		when(repository.findById(Email)).thenReturn(Optional.of(listCustomer.get(0)));
		assertEquals(listCustomer.get(0), service.getCustomerById(Email));
	}
}
