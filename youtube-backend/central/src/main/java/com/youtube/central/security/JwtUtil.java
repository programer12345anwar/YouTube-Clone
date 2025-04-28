package com.youtube.central.security;

import com.youtube.central.models.AppUser;
import com.youtube.central.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
@Slf4j
public class JwtUtil {
    @Autowired
    UserService userService;
    @Value("${central.security.secret.key}")
    String secretKey;

    Long expirationTime = 1200000L;//type casting long L

    //Create JWT Token on the basis of creneditial
    // As I mentioned in jwt token you are encrypting some information
    // What information we are going to encrypt we are going to encrypt user credentials
    // credentails = tiwarisomendra22@gmail.com:123456
    // We got the credentials generateToken function we will encrypt credentials with the help of algorithm and secret key

    public String generateToken(String credentials){
        log.info("user credential is:"+credentials);
        String jwtToken = Jwts.builder().setSubject(credentials)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        log.info("jwt token is: "+jwtToken);
        return jwtToken;
    }




    public String decryptToken(String token){
        String credentials =  Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return credentials;
    }



    public boolean isValidToken(String token){
        // decrypt token
        String credentials = this.decryptToken(token);
        String email = credentials.split(":")[0];
        String password = credentials.split(":")[1];
        // validate this token is correct or not
        AppUser user  = userService.getUserByEmail(email);
        if(user == null){
            return false;
        }
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

}