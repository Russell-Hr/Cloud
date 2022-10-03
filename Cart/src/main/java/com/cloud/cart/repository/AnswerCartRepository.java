package com.cloud.cart.repository;

import com.cloud.cart.entity.AnswerCart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AnswerCartRepository extends MongoRepository<AnswerCart, String> {
    List<AnswerCart> findByAnswer(String answer);
}
