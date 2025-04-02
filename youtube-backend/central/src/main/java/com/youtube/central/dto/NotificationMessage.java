package com.youtube.central.dto;

import lombok.Data;

@Data
public class NotificationMessage {
    String type; // user-registration, video-upload-notification, new-video-available
    String email; // user we need to send notification
    String name; // name of the user.
}
