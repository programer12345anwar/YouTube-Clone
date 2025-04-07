package com.youtube.central.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    @OneToMany // ChannelId vs UserId ,one channel can have many subscribers
    List<AppUser> subscribers;
    @OneToMany
    List<Video> videos;
    @OneToMany
    List<PlayList> playLists;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}