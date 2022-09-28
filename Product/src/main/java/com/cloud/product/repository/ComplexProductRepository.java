package com.cloud.product.repository;

import com.cloud.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComplexProductRepository
        extends MongoRepository<Product, String>, ProductRepositoryCustom {
}
