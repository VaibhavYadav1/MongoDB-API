package com.vaibhav.mongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vaibhav.mongodb.entities.Product;
import com.vaibhav.mongodb.reposi.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductRepository repository;
	
	@PostMapping
	public Mono<Product> addProduct(@RequestBody Product product){
		logger.info("Saving product : " + product.getName());
		return repository.save(product);
	}
	
	@GetMapping
	public Flux<Product> getproducts(){
		return repository.findAll();
	}
	
//	@GetMapping("/{id}")
//	Mono<Product> getproduct(@PathVariable("id") String id){
//		return repository.findById(id);
//	}
	
}