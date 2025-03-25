package com.george.countandmovement;

import com.george.countandmovement.model.dto.CustomerResponseDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerListener {

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void recibirCliente(CustomerResponseDTO customer) {
        System.out.println("📥 Cliente recibido en cuenta-service: " + customer);
        // Aquí podrías crear la cuenta automáticamente
    }
}