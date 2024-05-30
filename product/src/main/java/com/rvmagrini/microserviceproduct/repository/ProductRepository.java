package com.rvmagrini.microserviceproduct.repository;

import com.rvmagrini.microserviceproduct.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
