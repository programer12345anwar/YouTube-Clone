package com.youtube.central.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youtube.central.models.AppUser;
import com.youtube.central.service.UserService;

@RestController
@RequestMapping("/api/central/user")
public class UserController {

    private UserService userService;

    @Autowired //constructor based autowiring
    public UserController(UserService userService) {
        this.userService=userService;
    }
    @PostMapping("/register")
    public void registerUser(@RequestBody AppUser user){
        userService.registerUser(user);
        
    }
}
