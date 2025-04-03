package com.youtube.notification_api.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

import com.youtube.notification_api.dto.NotificationMessage;
import com.youtube.notification_api.enums.NotificationType;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CommonController {

    @RabbitListener(queues="notification-queue")
    public void consumeMessage(@Payload NotificationMessage message) {
        //it will run at the point of time some notification message payload is inserted inside the notification-queue and as the listener is subscibed to the queue it will automatically get triggered
        if(message.getType().equals(NotificationType.user_registration.toString())){

        }else if(message.getType().equals(NotificationType.channel_owner_subscriber_added.toString())){

        }else{
            
        }

    }
    
}
