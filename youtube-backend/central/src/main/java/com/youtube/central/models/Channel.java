package com.youtube.central.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "channels")
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    @ManyToOne
    AppUser user; // channel owner
    String description;
    String name;
    Double watchHours;
    boolean isMonetized;
    int totalViews;
    int totalLikeCount;
    int totalSubs;
    @OneToMany // ChannelId vs UserId
    List<AppUser> subscribers;
    @OneToMany
    List<Video> videos;
    @OneToMany
    List<PlayList> playLists;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}