package com.cloud.user.listener;

import com.cloud.user.entity.User;
import com.cloud.user.repository.ComplexUserRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@EnableRabbit
@Component
public class UserListener {
    @Autowired
    private ComplexUserRepository complexUserRepository;

    private final RabbitTemplate template;

    @Autowired
    public UserListener(RabbitTemplate template) {
        this.template = template;
    }

    @RabbitListener(queues = "queueUser")
    public void processQueueUser(String message) {
        User user = new User();
        user.setUserLine1("LINE_1 " + message);
        user.setUserLine2("LINE_2 " + message);
        complexUserRepository.createUser(user);
    }

    @RabbitListener(queues = "queueUserReader")
    public void processQueueUserReader(String message) {
        String users = complexUserRepository.findAll().toString();
        template.convertAndSend("queueCartReceiver", users);
    }

    @RabbitListener(queues = "queueCommon")
    public void processQueueCommon(String message) {
        User user = new User();
        user.setUserLine1("LINE_1 " + message);
        user.setUserLine2("LINE_2 " + message);
        complexUserRepository.createUser(user);
    }
}