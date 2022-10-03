package com.cloud.cart.controller;

import com.cloud.cart.listener.CartListener;
import com.cloud.cart.repository.ComplexAnswerCartRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @PostMapping("/create")
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

    @PostMapping("/common")
    public ResponseEntity<String> createCommonMessage() {
        String messageFromCart = "CommonMessageFromCart";
        template.setExchange("commonExchange");
        template.convertAndSend("queueCommon", messageFromCart);
        return ResponseEntity.ok("Added to a queueCommon");
    }

    @PostMapping("/key1")
    public ResponseEntity<String> createKeyMessageOne(@RequestBody Map<String, String> map) {
        template.setExchange("directExchange");
        template.convertAndSend(map.get("key"), map.get("message"));
        return ResponseEntity.ok("Added to queueCart and queueProduct");
    }

    @PostMapping("/key2")
    public ResponseEntity<String> createKeyMessageTwo(@RequestBody Map<String, String> map) {
        template.setExchange("directExchange");
        template.convertAndSend(map.get("key"), map.get("message"));
        return ResponseEntity.ok("Added to queueUser and queueCheckout");
    }

    @PostMapping("/topic1")
    public ResponseEntity<String> createTopicMessageOne(@RequestBody Map<String, String> map) {
        template.setExchange("topicExchange");
        template.convertAndSend(map.get("key"), map.get("message"));
        return ResponseEntity.ok("Added to queueCart and queueCheckout");
    }

    @PostMapping("/topic2")
    public ResponseEntity<String> createTopicMessageTwo(@RequestBody Map<String, String> map) {
        template.setExchange("topicExchange");
        template.convertAndSend(map.get("key"), map.get("message"));
        return ResponseEntity.ok("Added to queueUser and queueProduct");
    }
}
