package com.cloud.cart.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    private final AmqpTemplate template;

    @Autowired
    public CartController(AmqpTemplate template) {
        this.template = template;
    }

    @GetMapping("/direct")
    public ResponseEntity<String> create() {
        String messageFromCart = "DirectMessageFromCart";
        template.convertAndSend("queue", messageFromCart);
        return ResponseEntity.ok("Added to a queue");
    }
}
