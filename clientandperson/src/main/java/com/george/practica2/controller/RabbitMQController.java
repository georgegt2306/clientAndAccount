package com.george.practica2.controller;


import com.george.practica2.RabbitMQSender;  // Importa el servicio para enviar mensajes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitMQController {

    private final RabbitMQSender sender;

    @Autowired
    public RabbitMQController(RabbitMQSender sender) {
        this.sender = sender;
    }

    // Endpoint para probar el envío de mensajes
    /*@GetMapping("/send")
    public String sendMessage() {
        sender.sendMessage("Hola desde Spring Boot a RabbitMQ!");
        return "Mensaje enviado a RabbitMQ!";
    }*/
}