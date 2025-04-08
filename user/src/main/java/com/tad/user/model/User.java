package com.tad.user.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "contest_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class User {
    @Id
    @Column(name = "_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "nickname")
    String nickname;

    @Column(name = "fullname")
    String fullName;

    @Column(name = "organization")
    String organization;

    @Column(name = "email")
    String email;

    @Column(name = "create_at")
    Timestamp createdAt;

    @Column(name = "update_at")
    Timestamp updatedAt;

    @Column(name = "last_login")
    Timestamp lastLogin;

    @Column(name = "total_attempt")
    Integer totalAttempt;

}
