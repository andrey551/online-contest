package com.tad.course.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "laboratory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
public class Laboratory implements Serializable {

    @Id
    @Column(name = "_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "tags")
    String tags;

    @Column(name = "created")
    Timestamp created;

    @Column(name = "deadline")
    Timestamp deadline;

    @Column(name = "directory")
    String directory;

    @ManyToOne
    @JoinColumn(name="course_id", nullable=false)
    Course course;
}
