package com.cloud.checkout.repository;

import com.cloud.checkout.entity.Checkout;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComplexCheckoutRepository
        extends MongoRepository<Checkout, String>, CheckoutRepositoryCustom {
}
