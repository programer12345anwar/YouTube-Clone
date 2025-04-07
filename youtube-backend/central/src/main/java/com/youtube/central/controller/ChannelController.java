package com.youtube.central.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.youtube.central.dto.CreateChannelRequestBody;
import com.youtube.central.dto.VideoDetailsDTO;
import com.youtube.central.service.ChannelService;
import com.youtube.central.service.VideoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/central/channel")
@Slf4j
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @Autowired
    VideoService videoService;

    @PostMapping("/create")
    public void createChannel(@RequestBody CreateChannelRequestBody channelDetails){
        log.info("Channel details {}", channelDetails);
        channelService.createChannel(channelDetails);
    }

    // {subscribers : [1, 2, 3, 4, 5, 6, 7, 8]}
    @PutMapping("/{channelId}/subcribe")
    public void addSubscriber(@PathVariable UUID channelId,
                              @RequestParam UUID userId){
        // We need to call service

        channelService.addSubscriber(userId, channelId);
    }

    @PostMapping("/{channelId}/video/upload")
    public void saveVideoDetails(@RequestBody VideoDetailsDTO videoDetailsDTO,
                                 @PathVariable UUID channelId){
        videoService.saveVideoDetails(channelId, videoDetailsDTO);
    }
    
    
}
