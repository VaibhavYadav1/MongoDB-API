package com.vaibhav.mongodb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.vaibhav.mongodb.controller.ProductController;
import com.vaibhav.mongodb.entities.Product;
import com.vaibhav.mongodb.reposi.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class MongodbApplicationTests {

	@Autowired
	ProductController controller;
	
	@MockBean
	ProductRepository repo ;
	
	@Test
	void reactiveMongoDemoApplicationTest() {
		Product product = new Product(null , "Mouse" , "its cool" , 100d);
		Product saveProduct = new Product("abc123" , "Mouse" , "its cool" , 100d);

		when(repo.save(product)).thenReturn(Mono.just(saveProduct));
		StepVerifier.create(controller.addProduct(product))
		              .assertNext(p->{
		            	  assertNotNull(p);
		            	  assertNotNull(p.getId());
		            	  assertEquals("abc123", p.getId());
		            	  })
		              .expectComplete()
		              .verify();
		verify(repo).save(product);
	}

	@Test
	void testgetProduct() {
		when(repo.findAll()).thenReturn(Flux.just(
				new Product("abc123" , "Mouse" , "its cool" , 100d),
				new Product("abc124" , "Mouse" , "its cool" , 100d),
				new Product("abc125" , "Mouse" , "its cool" , 100d)));
		StepVerifier.create(controller.getproducts())
		.expectNextCount(3)
		.expectComplete()
		.verify();
		verify(repo).findAll();
	}
}
