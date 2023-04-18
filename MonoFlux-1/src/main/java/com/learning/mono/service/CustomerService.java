package com.learning.mono.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.mono.dao.CustomerDao;
import com.learning.mono.dto.Customer;

import reactor.core.publisher.Flux;

@Service
public class CustomerService {

	@Autowired
	public CustomerDao customerDao;
	
	public List<Customer> loadallCustomer(){
		long start=System.currentTimeMillis();
	List<Customer> customer = customerDao.getCustomer();
	long end=System.currentTimeMillis();
	System.out.println("Total execution time:"+ (end-start));
	return customer;
	
	}

	public Flux<Customer> loadallCustomerFlux() {
		long start=System.currentTimeMillis();
		Flux<Customer> customer = customerDao.getCustomerFlux();
		long end=System.currentTimeMillis();
		System.out.println("Total execution time:"+ (end-start));
		return customer;
	}
	
}
