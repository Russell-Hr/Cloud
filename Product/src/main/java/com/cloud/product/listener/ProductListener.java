package com.cloud.product.listener;

import com.cloud.product.entity.Product;
import com.cloud.product.repository.ComplexProductRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class ProductListener {
    @Autowired
    private ComplexProductRepository complexProductRepository;

    @RabbitListener(queues = "queue")
    public void processQueue(String message) {
        Product product = new Product();
        product.setProductLine1("LINE_1 " + message);
        product.setProductLine2("LINE_2 " + message);
        complexProductRepository.createProduct(product);

    }
}