package com.learning.mono.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.learning.mono.dao.CustomerDao;
import com.learning.mono.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

	@Autowired
	private CustomerDao customerDao;
	
	public Mono<ServerResponse> loadCustomer(ServerRequest request){
		Flux<Customer> list = customerDao.getCustomerFunctional();
		return ServerResponse.ok().body(list,Customer.class );
	}
	
	
	public Mono<ServerResponse> findCustomerId(ServerRequest request){
		Integer customerId = Integer.valueOf(request.pathVariable("input"));
//	    Mono<Customer> single = customerDao.getCustomerFunctional().filter(e->e.getId()==customerId)
//	    .take(1).single();
		Mono<Customer> single = customerDao.getCustomerFunctional().filter(e->e.getId()==customerId)
			    .next();
		return ServerResponse.ok().body(single,Customer.class);
		
	}
	
	public Mono<ServerResponse> saveCustomer(ServerRequest request){
		Mono<Customer> object = request.bodyToMono(Customer.class);
		Mono<String> response = object.map(e->e.getId()+" "+e.getName());
		return ServerResponse.ok().body(response,String.class);
	}
}
