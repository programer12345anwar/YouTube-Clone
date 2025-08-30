package com.youtube.notification_api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;

import java.util.Properties;

@Configuration
public class Config {

//    @Bean
//    public TemplateEngine getTemplateEngine(){
//        return new TemplateEngine();
//    }
//
//    @Bean
//    public JavaMailSender getJavaMailSender(){
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl(); //JavaMailSenderImpl → is a built-in helper class for sending emails.
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//        mailSender.setUsername("your email will be there");
//        mailSender.setPassword("your email app password");
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        return mailSender;
//    }  for best practices

//    @Bean
//    public TemplateEngine templateEngine() {
//        return new TemplateEngine();
//    }
    // No need to define JavaMailSender here,
    // Spring Boot auto-configures it using application.properties
}


/*
A template engine helps generate dynamic content by replacing placeholders in templates with runtime data. In Spring Boot, Thymeleaf is commonly used for emails and web pages.
Template Engine (in Spring Boot / Java)

Definition: A library that generates dynamic text/HTML by merging a template (with placeholders) and data (variables).

Why: Avoids string concatenation, makes dynamic content (emails, HTML pages, docs) maintainable.

How it works:

Template: Hello, ${name}!

Data: {name: "Anwar"}

Output: Hello, Anwar!

Popular Engines:

Thymeleaf (Spring Boot default, HTML & emails)

FreeMarker (lightweight, flexible)

Velocity (older, legacy use)

Example (Thymeleaf):
 */
 