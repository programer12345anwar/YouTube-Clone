package com.youtube.central.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youtube.central.dto.NotificationMessage;
import com.youtube.central.dto.VideoDetailsDTO;
import com.youtube.central.models.AppUser;
import com.youtube.central.models.Channel;
import com.youtube.central.models.Tag;
import com.youtube.central.models.Video;
import com.youtube.central.repository.VideoRepo;

@Service
public class VideoService {

    @Autowired
    ChannelService channelService;

    @Autowired
    TagService tagService;

    @Autowired
    VideoRepo videoRepo;

    @Autowired
    RabbitMqService rabbitMqService;

    String videoLink;

    public void saveVideo(Video video){
        // Video repository
         videoRepo.save(video);
    }

    public void saveVideoDetails(UUID channelId,
                                 VideoDetailsDTO videoDetailsDTO){
        // We need to get the channel object
        Channel channel  = channelService.getChannelById(channelId);
        Video video = new Video();
        video.setId(videoDetailsDTO.getId());
        video.setVideoLink(videoDetailsDTO.getVideoLink());
        video.setUpdatedAt(videoDetailsDTO.getUpdatedAt());
        video.setUploadDateTime(videoDetailsDTO.getUploadDateTime());
        // from video api we are getting list<String> tags but to create video model object we need tags as List<Tag>
        List<String> tags = videoDetailsDTO.getTags();
        // In this list we can get the tags which are already registered in our database or which are new tags
        // If a tag is already present in tags table then we need to get the tag from table
        // if not present i will create a new tag inside my table
        List<Tag> dbTagList = tagService.getAllTagsFromSystem(tags);
        video.setTags(dbTagList);
        // save these video details inside video table.
        this.saveVideo(video);
        this.videoLink = videoDetailsDTO.getVideoLink();
        // we need to update list videos of channel
        channel.getVideos().add(video);
        channelService.updateChannel(channel);
        // We need to notify all the subscribers that hey a new uploaded over the channel
        this.notifySubscibers(channel.getSubscribers());
    }

    public void notifySubscibers(List<AppUser> subscribers){
        for(int i = 0; i < subscribers.size(); i++){
            AppUser subscriber = subscribers.get(i);
            NotificationMessage notificationMessage = new NotificationMessage();
            notificationMessage.setName(videoLink);
            notificationMessage.setType("new_video");
            notificationMessage.setEmail(subscriber.getEmail());
            rabbitMqService.insertMessageToQueue(notificationMessage);
        }
    }



}