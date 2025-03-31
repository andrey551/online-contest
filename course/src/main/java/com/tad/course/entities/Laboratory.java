package com.tad.course.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "laboratory")
@Getter
@Setter
@NoArgsConstructor
public class Laboratory implements Serializable {

    @Id
    @Column(name = "_id")
    private UUID id;

    @Column(name = "course_id")
    private UUID courseId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "tags")
    private String tags;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "deadline")
    private Timestamp deadline;

    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    private Course course;
}
