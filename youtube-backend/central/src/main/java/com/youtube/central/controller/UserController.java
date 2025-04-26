package com.youtube.central.controller;

import com.youtube.central.dto.UserCredentialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserCredentialDTO credential){
        String resp= userService.userLogin(credential);
        if(resp.equals("Incorrect Password")){
            return new ResponseEntity("Incorrect Password", HttpStatus.UNAUTHORIZED);
        }else{
            return new ResponseEntity(resp,HttpStatus.OK);
        }
    }
}
