package com.youtube.central.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youtube.central.dto.NotificationMessage;

@Service 
public class RabbitMqService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public final String exchangeName = "notification-exchange";
    public final String routingKey = "notification-123";
    public void insertMessageToQueue(NotificationMessage message){
        // Logic to send notification message using RabbitMQ
        // with the combination of exchange and routing key we uniquely identify queue
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
    
}
