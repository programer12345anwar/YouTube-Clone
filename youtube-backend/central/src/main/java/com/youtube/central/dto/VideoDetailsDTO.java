package com.youtube.central.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class VideoDetailsDTO {
    String id; // This id will get generated inside firebase
    String name;
    String description;
    LocalDateTime uploadDateTime;
    LocalDateTime updatedAt;
    String videoLink;
    List<String> tags;
}
