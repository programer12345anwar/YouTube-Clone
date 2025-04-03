package com.youtube.notification_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.youtube.notification_api.dto.NotificationMessage;

@Service
public class CommonUserService {
    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    JavaMailSender javaMailSender;
    public void sendUserRegistrationEmail(NotificationMessage notificationMessage){
        //this function will send registration email to the user
        //so,email is of type html so we need o get html template
        //before getting html template we need to create variables inside html template
        Context context=new Context();
        context.setVariable("userName", notificationMessage.getName());
        context.setVariable("platformName", "Youtube");
        //we need to get our html template inform of string and all the variables populated inside html template
        String htmlEmailContent=templateEngine.process("user-registration-email", context);
        //templateEngine.process will insert values for all the variables defined inside the html template

        //i need to set this html content inside MimeMessage
    }
}
