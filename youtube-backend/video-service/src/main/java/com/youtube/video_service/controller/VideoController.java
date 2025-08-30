package com.youtube.video_service.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.youtube.video_service.dto.GeneralMessage;
import com.youtube.video_service.dto.VideoDetail;
import com.youtube.video_service.dto.VideoDetailRequestBody;
import com.youtube.video_service.exception.InvalidFileType;
import com.youtube.video_service.service.UploadService;

import lombok.extern.slf4j.Slf4j;

@RestController //@Controller + @ResponseBody annotations
@RequestMapping("/api/v1/video")
@Slf4j //it provides a common interface for logging, without tying the code to any specific logging framework (like Log4j, Logback, JUL).
// It makes code flexible, portable, and maintainable, since you can switch the logging implementation easily just by changing dependencies — no code changes needed.
public class VideoController {

  @Autowired
    UploadService uploadService;


    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public ResponseEntity uploadVideo(@RequestPart("videoFile")MultipartFile video,
                                      @RequestParam UUID channelId,
                                      @RequestPart("videodetails") VideoDetailRequestBody videoDetails){
        /*
        👉 @RequestParam is used to bind simple request parameters like strings, numbers, or form fields.
        👉 @RequestPart is used in multipart requests to handle files or JSON objects.

        In short: @RequestParam = simple data, @RequestPart = files/complex data.
         */

        try{
            log.info("received call to upload video at video controller");
            VideoDetail videoDetail = uploadService.uploadVideo(video, channelId, videoDetails);
            return new ResponseEntity(videoDetail, HttpStatus.CREATED); // 201
        }catch (InvalidFileType invalidFileType){
            GeneralMessage generalMessage = new GeneralMessage();
            generalMessage.setMessage(invalidFileType.getMessage());
            return new ResponseEntity(generalMessage, HttpStatus.BAD_REQUEST);
            //ResponseEntity is a Spring class used to return an HTTP response from a controller.
            //
            //It lets you control:
            //
            //Body (what data to send back)
            //
            //Status Code (like 200 OK, 404 Not Found, etc.)
            //
            //Headers (extra info like content type).
        }catch (Exception e){
            GeneralMessage generalMessage = new GeneralMessage();
            generalMessage.setMessage(e.getMessage());
            return new ResponseEntity(generalMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* function to upload video on imagekit 
    @PostMapping("/upload")
    public ResponseEntity uploadVideo(@RequestPart("videoFile") MultipartFile video) {
    log.info("video received at upload method");
    try {
        VideoDetail videoDetail = uploadService.uploadVideo(video);
        log.info("Video detail after upload is {}", videoDetail);
        return new ResponseEntity(videoDetail, HttpStatus.CREATED); // 201
    } catch (InvalidFileType invalidFileType) {
        GeneralMessage generalMessage = new GeneralMessage();
        generalMessage.setMessage(invalidFileType.getMessage());
        return new ResponseEntity(generalMessage, HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
        GeneralMessage generalMessage = new GeneralMessage();
        generalMessage.setMessage(e.getMessage());
        return new ResponseEntity(generalMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }
    */
}
