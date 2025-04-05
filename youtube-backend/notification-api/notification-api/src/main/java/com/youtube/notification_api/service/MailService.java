package com.youtube.notification_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailService{

    @Autowired
    JavaMailSender javaMailSender;
    public void sendEmail(MimeMessage message){
        javaMailSender.send(message);
    }
}