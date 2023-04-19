package com.learning.mono.dao;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.learning.mono.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomerDao {

	private static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Customer> getCustomer(){
		 List<Customer> list = IntStream.range(1, 10)
				 .peek(CustomerDao::sleepExecution)
				.peek(i->System.out.println("processing count"+i))
		.mapToObj(i->new Customer(i,"customer"+i))
		.collect(Collectors.toList());
		return list;
		
	}

	public Flux<Customer> getCustomerFlux() {
		 return Flux.range(1, 10)
		 .delayElements(Duration.ofSeconds(1))
		.doOnNext(i->System.out.println("processing count"+i))
		.map(i->new Customer(i,"customer"+i));
	}
	
	public Flux<Customer> getCustomerFunctional() {
		 return Flux.range(1, 10)
				 .delayElements(Duration.ofMillis(1000))
		.doOnNext(i->System.out.println("processing count"+i))
		.map(i->new Customer(i,"customer"+i));
	}
}
