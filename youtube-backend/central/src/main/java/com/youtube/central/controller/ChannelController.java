package com.youtube.central.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youtube.central.dto.CreateChannelRequestBody;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/central/channel")
@Slf4j
public class ChannelController {
    @PostMapping("/create")
    public void createChannel(@RequestBody CreateChannelRequestBody channelDetails){
        log.info("Channel details {}", channelDetails);
    }
    
}
