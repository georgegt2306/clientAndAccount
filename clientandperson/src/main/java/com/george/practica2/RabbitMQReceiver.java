package com.george.practica2;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceiver {

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void receiveMessage(String message) {
        System.out.println("Mensaje recibido: " + message);
    }
}
