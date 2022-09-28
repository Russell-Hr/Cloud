package com.cloud.checkout.repository;

import com.cloud.checkout.entity.Checkout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CheckoutRepositoryCustomImpl implements CheckoutRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void createCheckout(Checkout checkout) {
        mongoTemplate.save(checkout);
    }
}