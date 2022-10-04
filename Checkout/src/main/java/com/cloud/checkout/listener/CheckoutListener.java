package com.cloud.checkout.listener;

import com.cloud.checkout.entity.Checkout;
import com.cloud.checkout.repository.ComplexCheckoutRepository;
import libs.Now;
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
        Now now = new Now();
        Checkout checkout = new Checkout();
        checkout.setCheckoutLine1("LINE_1 " + message + " " + now.createNow());
        checkout.setCheckoutLine2("LINE_2 " + message + " " + now.createNow());
        complexCheckoutRepository.createCheckout(checkout);
    }
}