package com.tad.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "contest_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "organization")
    private String organization;

    @Column(name = "email")
    private String email;

    @Column(name = "create_at")
    private Timestamp createdAt;

    @Column(name = "update_at")
    private Timestamp updatedAt;

    @Column(name = "last_login")
    private Timestamp lastLogin;

    @Column(name = "total_attempt")
    private Integer totalAttempt;

}
