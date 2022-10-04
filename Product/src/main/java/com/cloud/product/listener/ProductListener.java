package com.cloud.product.listener;

import com.cloud.product.entity.Product;
import com.cloud.product.repository.ComplexProductRepository;
import libs.Now;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class ProductListener {
    @Autowired
    private ComplexProductRepository complexProductRepository;

    @RabbitListener(queues = "queueProduct")
    public void processQueue(String message) {
        Now now = new Now();
        Product product = new Product();
        product.setProductLine1("LINE_1 " + message + " " + now.createNow());
        product.setProductLine2("LINE_2 " + message + " " + now.createNow());
        complexProductRepository.createProduct(product);
    }

    @RabbitListener(queues = "queueCommon")
    public void processQueueCommon(String message) {
        Now now = new Now();
        Product product = new Product();
        product.setProductLine1("LINE_1 " + message + " " + now.createNow());
        product.setProductLine2("LINE_2 " + message + " " + now.createNow());
        complexProductRepository.createProduct(product);
    }
}