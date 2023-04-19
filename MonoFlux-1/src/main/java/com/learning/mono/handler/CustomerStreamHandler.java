package com.learning.mono.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.learning.mono.dao.CustomerDao;
import com.learning.mono.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

	@Autowired
	public CustomerDao customerDao;
	public Mono<ServerResponse> getCustomer(ServerRequest request){
		Flux<Customer> customerStream = customerDao.getCustomerFunctional();
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(customerStream, Customer.class);
	}
}
