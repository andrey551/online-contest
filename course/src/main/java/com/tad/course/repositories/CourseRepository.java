package com.tad.course.repositories;

import com.tad.course.entities.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository
        extends BaseRepository<Course, UUID>{

    List<Course> findByTeacherId(UUID teacherId);

    @EntityGraph(attributePaths = {"students"})
    @Query("SELECT a FROM Course a WHERE :studentId MEMBER OF a.students")
    Optional<List<Course>> findByStudentId(UUID studentId);
}
