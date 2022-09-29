package com.cloud.cart.listener;

import com.cloud.cart.entity.Cart;
import com.cloud.cart.repository.ComplexCartRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class CartListener {
    @Autowired
    private ComplexCartRepository complexCartRepository;

    @RabbitListener(queues = "queueCart")
    public void processQueueCart(String message) {
        Cart cart = new Cart();
        cart.setCartLine1("LINE_1 " + message);
        cart.setCartLine2("LINE_2 " + message);
        complexCartRepository.createCart(cart);
    }

    @RabbitListener(queues = "queueCommon")
    public void processQueueCommon(String message) {
        Cart cart = new Cart();
        cart.setCartLine1("LINE_1 " + message);
        cart.setCartLine2("LINE_2 " + message);
        complexCartRepository.createCart(cart);
    }
}