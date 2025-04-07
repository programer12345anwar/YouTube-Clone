package com.youtube.video_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/video")
public class VideoController {
    
    @PostMapping("/upload")
    public void uploadVideo(@RequestPart("file") MultipartFile video) {

    }
}
