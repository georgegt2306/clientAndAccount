package com.george.practica2;

import com.george.practica2.config.RabbitConfig;
import com.george.practica2.model.dto.CustomerResponseDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    private final RabbitTemplate rabbitTemplate;
    private final String exchangeName;
    private final String routingKey;

    public RabbitMQSender(RabbitTemplate rabbitTemplate,
                             @Value("${rabbitmq.exchange.name}") String exchangeName,
                             @Value("${rabbitmq.routing.key}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
    }

    public void sendCustomer(CustomerResponseDTO customer) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, customer);
        System.out.println("📤 Cliente enviado a RabbitMQ: " + customer);
    }
}
