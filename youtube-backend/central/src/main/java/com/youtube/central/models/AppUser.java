package com.youtube.central.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//or use @Data
@Table(name = "Users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String name;
    @Column(unique = true)
    String email;
    String password;
    @Column(unique = true)
    Long phoneNumber;
    LocalDate dob;
    String gender;
    String country;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
