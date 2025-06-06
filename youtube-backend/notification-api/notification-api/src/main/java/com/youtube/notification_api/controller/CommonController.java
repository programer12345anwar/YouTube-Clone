
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

    @RabbitListener(queues = "notification-queue")
    public void consumeMessage(@Payload NotificationMessage message) throws Exception {
        // It will run at the point of time some notification message payload is inserted inside
        // Notification-queue and as the listner is subscribed to the queue it will automatically get triggered
        log.info("Notification Message recieved successfully payload = " + message.toString());
        try{
            if(message.getType().equals(NotificationType.user_registration.toString())){
                log.info("Calling common user service to registration mail");
                commonUserService.sendUserRegistrationEmail(message);
            }else if(message.getType().equals(NotificationType.subscriber_added.toString())){
                // If the message type is subscriber added i need to send a mail to the user that a new subscriber is added in your channel
                log.info("Message type is subscriber_added");
                commonUserService.sendSubscriberAddedMail(message);
            }else if(message.getType().equals(NotificationType.create_channel.toString())){
                log.info("CommonController: Type of notification is create_channel calling commonuserservice");
                commonUserService.sendCreateChannelNotification(message);
            }else if(message.getType().equals(NotificationType.new_video.toString())){
                log.info("got type of message as new_video");
                commonUserService.notifyNewVideoUploadedToSubscriber(message);
            }
        }catch(Exception e){
            log.error(e.getMessage());
        }
        
    }
}
