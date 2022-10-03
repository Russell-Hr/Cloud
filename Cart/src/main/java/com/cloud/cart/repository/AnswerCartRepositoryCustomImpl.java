package com.cloud.cart.repository;

import com.cloud.cart.entity.AnswerCart;
import com.cloud.cart.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerCartRepositoryCustomImpl implements AnswerCartRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void createAnswerCart(AnswerCart answerCart) {
        mongoTemplate.save(answerCart);
    }
}
