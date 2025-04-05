package com.youtube.notification_api.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

import com.youtube.notification_api.dto.NotificationMessage;
import com.youtube.notification_api.enums.NotificationType;
import com.youtube.notification_api.service.CommonUserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CommonController {
    @Autowired
    CommonUserService commonUserService;

    @RabbitListener(queues="notification-queue")
    public void consumeMessage(@Payload NotificationMessage message) throws Exception {
        //it will run at the point of time some notification message payload is inserted inside the notification-queue and as the listener is subscibed to the  queue it will automatically get triggered

        log.info("Notification Message received successfully payload= "+message.toString());
        if(message.getType().equals(NotificationType.user_registration.toString())){
            log.info("Calling common user service to send registration mail");
            commonUserService.sendUserRegistrationEmail(message);
        }else if(message.getType().equals(NotificationType.channel_owner_subscriber_added.toString())){

        }else{
            
        }

    }
    
}
