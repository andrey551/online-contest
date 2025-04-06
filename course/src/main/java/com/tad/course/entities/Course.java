package com.tad.course.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity()
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder
public class Course implements Serializable {
    @Id
    @Column(name = "_id")
    UUID id;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;


    @Column(name = "teacher_id")
    UUID teacherId;

    @Column(name = "teacher_name")
    String teacherName;

    @Column(name = "semester")
    String semester;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Laboratory> laboratories;

    @ElementCollection
    @CollectionTable(name = "student_ids", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "student_id")
    List<UUID> students;
}
