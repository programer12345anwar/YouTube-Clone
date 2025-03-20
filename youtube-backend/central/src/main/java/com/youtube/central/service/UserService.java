package com.youtube.central.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youtube.central.dto.NotificationMessage;
import com.youtube.central.models.AppUser;
import com.youtube.central.repository.AppUserRepo;

@Service
public class UserService {

    private AppUserRepo appUserRepo;
    private RabbitMqService rabbitMqService;
    @Autowired
    public UserService(AppUserRepo appUserRepo, RabbitMqService rabbitMqService) {
        this.appUserRepo = appUserRepo;
        this.rabbitMqService = rabbitMqService;
    }
    public void registerUser(AppUser user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        appUserRepo.save(user);
        //insert user registration message payload to rabbitmq
        NotificationMessage notificationMessage=new NotificationMessage();
        notificationMessage.setPurpose("User-Registration");
        notificationMessage.setEmail(user.getEmail());
        rabbitMqService.sendNotificationMessageToQueue(notificationMessage);
         
    }
    
}
