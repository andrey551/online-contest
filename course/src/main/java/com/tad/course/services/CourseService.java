package com.tad.course.services;


import com.tad.course.DTOs.enums.TransactionStatus;
import com.tad.course.DTOs.request.CourseRequest;
import com.tad.course.DTOs.response.CourseResponse;
import com.tad.course.DTOs.response.CoursesResponse;
import com.tad.course.entities.Course;
import com.tad.course.exceptions.CourseNotFound;
import com.tad.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tad.course.mapper.CourseMapper.toCourse;
import static com.tad.course.mapper.CourseMapper.toRawCourse;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseResponse getCourseById(UUID id) {
        try {
            Course course = courseRepository.findById(id).orElse(null);

            if (course == null) {
                throw new CourseNotFound();
            }

            return new CourseResponse(TransactionStatus.SUCCESS,
                                        null,
                                        toRawCourse(course));

        } catch (CourseNotFound e) {
            return new CourseResponse(
                    TransactionStatus.NOT_FOUND,
                    "Course not found",
                    null
            );
        } catch (Exception e) {
            return new CourseResponse(
                    TransactionStatus.INTERNAL_ERROR,
                    null,
                    null
            );
        }
    }

    public CourseResponse insertCourse(CourseRequest courseRequest,
                                        String teacherName,
                                        UUID teacherId) {
        try {
            Course course = toCourse(courseRequest);

            course.setTeacherName(teacherName);
            course.setTeacherId(teacherId);

            course.setId(UUID.randomUUID());

            Course courseSaved = courseRepository.save(course);

            return new CourseResponse(TransactionStatus.SUCCESS,
                    null,
                    toRawCourse(courseSaved));
        } catch (Exception e) {
            return new CourseResponse(TransactionStatus.INTERNAL_ERROR,
                                        "Internal error",
                                        null);
        }
    }
}
