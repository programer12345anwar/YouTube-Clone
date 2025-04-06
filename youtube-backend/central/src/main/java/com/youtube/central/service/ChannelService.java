package com.youtube.central.service;

import com.youtube.central.exception.UserNotFound;
import com.youtube.central.models.AppUser;
import com.youtube.central.models.Channel;
import com.youtube.central.repository.ChannelRepo;
import com.youtube.central.request.CreateChannelRequestBody;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class ChannelService {

    @Autowired
    UserService userService;

    @Autowired
    ChannelRepo channelRepo;

    @Autowired
    RabbitMqService rabbitMqService;

    public void createChannel(CreateChannelRequestBody channelDetails) {
        log.info("Channel details {}", channelDetails);
        String email=channelDetails.getUserEmail();
        //we need to check with this email user is registered or not
        AppUser user=userService.getUserByEmail(email);
        //if user is registered then we need to create channel
        if(user==null){
            //user is not registered
            //  thow new UserNotFound("user with email "+email+" is not registered");
            throw new UserNotFound(String.format("user with email %s does not exist in our system",email));
        }
        //we need to create channel
        Channel channel=new Channel();
        channel.setCreatedAt(LocalDateTime.now());
        channel.setUpdatedAt(LocalDateTime.now());
        channel.setIsMonetized(false);
        channel.setUser(user);
        channel.setName(channelDetails.getChannelName());
        channel.setDescription(channelDetails.getDescription());

        //call repository layer to save the channel
        channelRepo.save(channel);
        //Insert channel creation message payload inside rabbit mq queue.
         NotificationMessage notificationMessage = new NotificationMessage();
         notificationMessage.setName(user.getName());
         notificationMessage.setEmail(user.getEmail());
         notificationMessage.setType("create_channel");
         //to upload message to queue we need rabbitMqService
         rabbitMqService.insertMessageToQueue(notificationMessage);
    }
}
