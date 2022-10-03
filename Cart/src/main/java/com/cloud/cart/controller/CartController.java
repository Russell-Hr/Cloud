package com.cloud.cart.controller;

import com.cloud.cart.entity.AnswerCart;
import com.cloud.cart.listener.CartListener;
import com.cloud.cart.repository.ComplexAnswerCartRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    private final RabbitTemplate template;

    @Autowired
    CartListener cartListener;

    @Autowired
    private ComplexAnswerCartRepository complexAnswerCartRepository;

    @Autowired
    public CartController(RabbitTemplate template) {
        this.template = template;
    }

    @GetMapping("/create")
    public ResponseEntity<String> createDirectMessage() {
        String messageFromCart = "DirectMessageFromCart";
        template.convertAndSend("queueUser", messageFromCart);
        return ResponseEntity.ok("Added to a queueUser");
    }

    @GetMapping("/read")
    public String readDirectMessage() {
        String messageFromCart = "DirectMessageFromCartFindAll";
        template.convertAndSend("queueUserReader", messageFromCart);
        while (complexAnswerCartRepository.findAll().size() == 0) {
        }
        String answer = complexAnswerCartRepository.findAll().toString();
        complexAnswerCartRepository.deleteAll();
        return answer;
    }

    @GetMapping("/common")
    public ResponseEntity<String> createCommonMessage() {
        String messageFromCart = "CommonMessageFromCart";
        template.setExchange("commonExchange");
        template.convertAndSend("queueCommon", messageFromCart);
        return ResponseEntity.ok("Added to a queueCommon");
    }

    @GetMapping("/key1")
    public ResponseEntity<String> createKeyMessageOne() {
        template.setExchange("directExchange");
        template.convertAndSend("toCartAndProduct", "KeyMessageToCartAndProduct");
        return ResponseEntity.ok("Added to queueCart and queueProduct");
    }

    @GetMapping("/key2")
    public ResponseEntity<String> createKeyMessageTwo() {
        template.setExchange("directExchange");
        template.convertAndSend("toUserAndCheckout", "KeyMessageToUserAndCheckout");
        return ResponseEntity.ok("Added to queueUser and queueCheckout");
    }

    @GetMapping("/topic1")
    public ResponseEntity<String> createTopicMessageOne() {
        template.setExchange("topicExchange");
        template.convertAndSend("toCartAndCheckout.something", "TopicMessageToCartAndCheckout");
        return ResponseEntity.ok("Added to queueCart and queueCheckout");
    }

    @GetMapping("/topic2")
    public ResponseEntity<String> createTopicMessageTwo() {
        template.setExchange("topicExchange");
        template.convertAndSend("something.toUserAndProduct", "TopicMessageToUserAndProduct");
        return ResponseEntity.ok("Added to queueUser and queueProduct");
    }

}
