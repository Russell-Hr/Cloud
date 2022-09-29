package com.cloud.checkout.listener;

import com.cloud.checkout.entity.Checkout;
import com.cloud.checkout.repository.ComplexCheckoutRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class CheckoutListener {
    @Autowired
    private ComplexCheckoutRepository complexCheckoutRepository;

    @RabbitListener(queues = "queueCheckout")
    public void processQueue(String message) {
        Checkout checkout = new Checkout();
        checkout.setCheckoutLine1("LINE_1 " + message);
        checkout.setCheckoutLine2("LINE_2 " + message);
        complexCheckoutRepository.createCheckout(checkout);
    }

    @RabbitListener(queues = "queueCommon")
    public void processQueueCommon(String message) {
        Checkout checkout = new Checkout();
        checkout.setCheckoutLine1("LINE_1 " + message);
        checkout.setCheckoutLine2("LINE_2 " + message);
        complexCheckoutRepository.createCheckout(checkout);
    }
}