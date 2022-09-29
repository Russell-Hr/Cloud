package com.cloud.cart.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    private final RabbitTemplate template;

    @Autowired
    public CartController(RabbitTemplate template) {
        this.template = template;
    }

    @GetMapping("/direct")
    public ResponseEntity<String> createDirectMessage() {
        String messageFromCart = "DirectMessageFromCart";
        //template.setExchange("commonExchange");
        template.convertAndSend("queueUser", messageFromCart);
        return ResponseEntity.ok("Added to a queueUser");
    }

    @GetMapping("/common")
    public ResponseEntity<String> createCommonMessage() {
        String messageFromCart = "CommonMessageFromCart";
        template.setExchange("commonExchange");
        template.convertAndSend("queueCommon", messageFromCart);
        return ResponseEntity.ok("Added to a queueCommon");
    }
}
