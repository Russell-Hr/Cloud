package com.cloud.product.repository;

import com.cloud.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void createProduct(Product product) {
        mongoTemplate.save(product);
    }
}