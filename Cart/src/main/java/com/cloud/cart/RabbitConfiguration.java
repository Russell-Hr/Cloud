package com.cloud.cart;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    Logger logger = LoggerFactory.getLogger(RabbitConfiguration.class);

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue queueCart() {
        return new Queue("queueCart");
    }

    @Bean
    public Queue queueCheckout() {
        return new Queue("queueCheckout");
    }

    @Bean
    public Queue queueProduct() {
        return new Queue("queueProduct");
    }

    @Bean
    public Queue queueUser() {
        return new Queue("queueUser");
    }

    @Bean
    public Queue queueCommon() {
        return new Queue("queueCommon");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("commonExchange");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding bindingCart() {
        return BindingBuilder.bind(queueCart()).to(directExchange()).with("toCartAndProduct");
    }

    @Bean
    public Binding bindingProduct() {
        return BindingBuilder.bind(queueProduct()).to(directExchange()).with("toCartAndProduct");
    }

    @Bean
    public Binding bindingUser() {
        return BindingBuilder.bind(queueUser()).to(directExchange()).with("toUserAndCheckout");
    }

    @Bean
    public Binding bindingCheckout() {
        return BindingBuilder.bind(queueCheckout()).to(directExchange()).with("toUserAndCheckout");
    }

    @Bean
    public Binding bindingCommon() {
        return BindingBuilder.bind(queueCommon()).to(fanoutExchange());
    }
}
