package com.youtube.central.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserWatchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    UUID userId;
    String videoId;
    int count;
    boolean isLiked;
    LocalDateTime lastWatched;
}
