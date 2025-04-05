package com.youtube.notification_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.youtube.notification_api.dto.NotificationMessage;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonUserService {
    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    MailService mailService;

    @Value("${youtube.platform.name}")
    String platformName;

   
    public void sendUserRegistrationEmail(NotificationMessage notificationMessage) throws Exception{
        //this function will send registration email to the user
        //so,email is of type html so we need o get html template
        //before getting html template we need to create variables inside html template
        log.info("Inside common user service");
        Context context=new Context();
        context.setVariable("userName", notificationMessage.getName());
        context.setVariable("platformName",platformName);
        //we need to get our html template inform of string and all the variables populated inside html template
        String htmlEmailContent=templateEngine.process("user-registration-email", context);
        //templateEngine.process will insert values for all the variables defined inside the html template

        //i need to set this html content inside MimeMessage,so for that we need to create MimeMessage
        log.info("Email template loaded "+htmlEmailContent);
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage);
        //mimeMessageHelper.setFrom("z7KpR@example.com");
        mimeMessageHelper.setTo(notificationMessage.getEmail());
        mimeMessageHelper.setSubject("Welcome to Youtube");
        mimeMessageHelper.setText(htmlEmailContent, true);
        log.info("Mememessage created calling mail service to send mail");
        mailService.sendEmail(mimeMessage);

    }
}

/*  // This function will send registration email to the user
        // So, email is of type html So we need to get html template
        // Before getting html template we need to create variables inside html template

        log.info("Inside Common user service: " + mailHost);
        Context context = new Context();
        context.setVariable("userName", notificationMessage.getName());
        context.setVariable("platformName", platformName);
        // We need to get our html template inform of string and all the variables popluated inside html template
        String htmlEmailContent = templateEngine.process("user-registration-email", context);
        // templateEnine.process will insert values for all the variables defined inside html template
        // I need to set this html content inside MimeMessage
        //log.info("Email template loaded: " + htmlEmailContent);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(notificationMessage.getEmail());
        mimeMessageHelper.setSubject("Welcome to Youtube!");
        mimeMessageHelper.setText(htmlEmailContent, true);
        log.info("Mimemessage created calling mail service to send mail");
        mailService.sendEmail(mimeMessage); */