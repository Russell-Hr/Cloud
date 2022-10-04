package com.cloud.checkout.repository;

import com.cloud.checkout.entity.Checkout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CheckoutRepository extends MongoRepository<Checkout, String> {
    List<Checkout> findByCheckoutLine1AndCheckoutLine2(String checkoutLine1, String checkoutLine2);
    List<Checkout> findByCheckoutLine1(String checkoutLine1);
    List<Checkout> findByCheckoutLine2(String checkoutLine2);
}