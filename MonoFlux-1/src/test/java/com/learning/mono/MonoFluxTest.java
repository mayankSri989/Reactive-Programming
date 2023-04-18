package com.learning.mono;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

//	@Test
//	public void testMono() {
//		Mono<Object> monoString =Mono.just("mayankSri")
//				.then(Mono.error(new RuntimeException("qwe")))
//				.log();
//		monoString.subscribe(System.out::println);
//	}
	
	@Test
	public void testFlux() {
		Flux<String> fluxString= Flux.
				just("Mayank1","Mayank2","Mayank3")
				.concatWith(Flux.error(new RuntimeException("asd")))
				.log();
		fluxString.subscribe(System.out::println);
	}
}
