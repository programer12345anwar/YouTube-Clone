package com.youtube.central.controller;

import com.youtube.central.dto.UserCredentialDTO;
import com.youtube.central.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.youtube.central.models.AppUser;
import com.youtube.central.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/central/user")
@Slf4j
public class UserController {

    @Autowired
    JwtUtil jwtUtil;

    private UserService userService;

    @Autowired //constructor based autowiring
    public UserController(UserService userService) {
        this.userService=userService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody AppUser user){
        userService.registerUser(user);
        String credential=user.getEmail()+":"+user.getPassword();
        log.info("user controller class credential "+credential);
        return jwtUtil.generateToken(credential);
    }


    @GetMapping("/login")
    public ResponseEntity loginUser(@RequestBody UserCredentialDTO credential){
        String resp= userService.userLogin(credential);
        if(resp.equals("Incorrect Password")){
            return new ResponseEntity("Incorrect Password", HttpStatus.UNAUTHORIZED);
        }else{
            String token=jwtUtil.generateToken(resp);
            return new ResponseEntity(token,HttpStatus.OK);
        }
    }



}
