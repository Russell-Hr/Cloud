package com.cloud.cart.repository;

import com.cloud.cart.entity.AnswerCart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComplexAnswerCartRepository extends MongoRepository<AnswerCart, String>, AnswerCartRepositoryCustom {
}
