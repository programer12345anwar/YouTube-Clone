package com.youtube.central.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youtube.central.models.AppUser;
import com.youtube.central.repository.AppUserRepo;

@Service
public class UserService {

    private AppUserRepo appUserRepo;
    @Autowired
    public UserService(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }
    public AppUser registerUser(AppUser user) {
        appUserRepo.save(user);
        return user;
    }
    
}
