package com.cloud.cart.repository;

import com.cloud.cart.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CartRepositoryCustomImpl implements CartRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void createCart(Cart cart) {
        mongoTemplate.save(cart);
    }
}