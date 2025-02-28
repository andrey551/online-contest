package com.tad.comment.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "problem_id")
    private UUID problemId;

    @Column(name = "create_at")
    private Timestamp createdAt;

    @Column(name = "update_at")
    private Timestamp updatedAt;

    @Column(name = "content")
    private String content;


}
