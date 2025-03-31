package com.tad.course.repositories;

import com.tad.course.entities.Course;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository
        extends BaseRepository<Course, UUID>{
}
