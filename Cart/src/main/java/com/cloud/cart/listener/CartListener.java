package com.cloud.cart.listener;

import com.cloud.cart.entity.AnswerCart;
import com.cloud.cart.entity.Cart;
import com.cloud.cart.repository.ComplexAnswerCartRepository;
import com.cloud.cart.repository.ComplexCartRepository;
import libs.Now;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class CartListener {
    @Autowired
    private ComplexCartRepository complexCartRepository;

    @Autowired
    private ComplexAnswerCartRepository complexAnswerCartRepository;

    @RabbitListener(queues = "queueCart")
    public void processQueueCart(String message) {
        Now now = new Now();
        Cart cart = new Cart();
        cart.setCartLine1("LINE_1 " + message + " " + now.createNow());
        cart.setCartLine2("LINE_2 " + message + " " + now.createNow());
        complexCartRepository.createCart(cart);
    }

    @RabbitListener(queues = "queueCartReceiver")
    public void processQueueCartReceiver(String message) {
        AnswerCart answerCart = new AnswerCart();
        answerCart.setAnswer(message);
        complexAnswerCartRepository.createAnswerCart(answerCart);
    }
}