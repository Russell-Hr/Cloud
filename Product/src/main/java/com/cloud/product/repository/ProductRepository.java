package com.cloud.product.repository;

import com.cloud.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByProductLine1AndProductLine2(String productLine1, String productLine2);
    List<Product> findByProductLine1(String productLine1);
    List<Product> findByProductLine2(String productLine2);
}