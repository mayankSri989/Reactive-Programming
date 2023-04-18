package com.learning.mono.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.mono.dto.Customer;
import com.learning.mono.service.CustomerService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/list")
public class CustomerController {

	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/")
	public List<Customer> getList(){
		return customerService.loadallCustomer();
	}
	
	@GetMapping(value="/flux",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Customer> getListFlux(){
		return customerService.loadallCustomerFlux();
	}
}
