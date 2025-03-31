package com.tad.course.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity()
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
public class Course implements Serializable {
    @Id
    @Column(name = "_id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;


    @Column(name = "teacher_id")
    private UUID teacherId;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "semester")
    private String semester;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Laboratory> laboratories;
}
