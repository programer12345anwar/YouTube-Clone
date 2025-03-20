package com.youtube.central.dto;

import lombok.Data;

@Data
public class NotificationMessage {
    private String purpose;//user-registration,video upload notification,forgot password notification etc
    private String email;//email of the user
    
}
