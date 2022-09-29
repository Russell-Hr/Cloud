package com.cloud.user.listener;

import com.cloud.user.entity.User;
import com.cloud.user.repository.ComplexUserRepository;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class UserListener {
    @Autowired
    private ComplexUserRepository complexUserRepository;

    @RabbitListener(queues = "queue")
    public void processQueue(String message) {
        User user = new User();
        user.setUserLine1("LINE_1 " + message);
        user.setUserLine2("LINE_2 " + message);
        complexUserRepository.createUser(user);
    }
}