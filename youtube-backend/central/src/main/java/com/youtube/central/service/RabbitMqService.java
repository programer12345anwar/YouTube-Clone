package com.youtube.central.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youtube.central.dto.NotificationMessage;

@Service
public class RabbitMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    public void sendNotificationMessageToQueue(NotificationMessage message) {
        // Logic to send notification message using RabbitMQ
    }
    
}
