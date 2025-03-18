package com.george.practica2.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue("testQueue", false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("testExchange");
    }
}
