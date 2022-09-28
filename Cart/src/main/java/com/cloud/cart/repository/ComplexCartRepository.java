package com.cloud.cart.repository;

import com.cloud.cart.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComplexCartRepository
        extends MongoRepository<Cart, String>, CartRepositoryCustom {
}
