package com.youtube.notification_api.service;

import com.youtube.notification_api.dto.NotificationMessage;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@Slf4j
public class CommonUserService {

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    MailService mailService;

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${youtube.platform.name}")
    String platformName;

    @Value("${spring.mail.host}")
    private String mailHost;

    public void sendUserRegistrationEmail(NotificationMessage notificationMessage) throws Exception{
        // This function will send registration email to the user
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
        mailService.sendEmail(mimeMessage);
    }

    public void sendCreateChannelNotification(NotificationMessage message) throws Exception{
        log.info("CommonUserService:  Inside sendCreateChannelNotification method");
        // We need to send html kind of email that your channel got created over our portal
        Context context = new Context();//Context is a class from thymeleaf, it is just like a container of variables 
        context.setVariable("userName", message.getName());
        context.setVariable("platformName", platformName);
        String htmlEmailContent = templateEngine.process("create-channel-email", context);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(message.getEmail());
        mimeMessageHelper.setSubject("Your Channel is Live!");
        mimeMessageHelper.setText(htmlEmailContent, true);
        log.info("Mimemessage created calling mail service to send mail");
        mailService.sendEmail(mimeMessage);
    }

    public void sendSubscriberAddedMail(NotificationMessage message) throws Exception{
        Context context = new Context();
        context.setVariable("channelName", message.getName());
        context.setVariable("platformName", platformName);

        String htmlTemplate = templateEngine.process("subscriber-added", context);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setTo(message.getEmail());
        helper.setText(htmlTemplate, true);
        helper.setSubject("New Subscriber Alert!");
        mailService.sendEmail(mimeMessage);
    }


    public void notifyNewVideoUploadedToSubscriber(NotificationMessage notificationMessage) throws Exception{
        String subscriberEmail = notificationMessage.getEmail();
        String subscriberName = notificationMessage.getName();

        Context context = new Context();
        context.setVariable("subscriberName", subscriberEmail);
        context.setVariable("videoLink", notificationMessage.getName());

        String htmlTemplate = templateEngine.process("new-video-notification", context);
        log.info(htmlTemplate);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setTo(subscriberEmail);
        helper.setText(htmlTemplate, true);
        helper.setSubject("New Video Alert !!");
        mailService.sendEmail(mimeMessage);
    }
}
 