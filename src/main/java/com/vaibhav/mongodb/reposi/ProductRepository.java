package com.vaibhav.mongodb.reposi;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.vaibhav.mongodb.entities.Product;

public interface ProductRepository extends ReactiveMongoRepository <Product , String> {

}
