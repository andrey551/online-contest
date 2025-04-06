package com.tad.course.services;


import com.tad.course.DTOs.request.CourseAddStudentRequest;
import com.tad.course.constants.enums.TransactionStatus;
import com.tad.course.DTOs.request.CourseRequest;
import com.tad.course.DTOs.response.CourseResponse;
import com.tad.course.DTOs.response.CoursesResponse;
import com.tad.course.entities.Course;
import com.tad.course.exceptions.CourseNotFoundException;
import com.tad.course.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.tad.course.constants.TransactionMessage.*;
import static com.tad.course.mapper.CourseMapper.*;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseResponse getCourseById(UUID id) {
        try {
            Course course = courseRepository.findById(id).orElse(null);

            if (course == null) {
                throw new CourseNotFoundException();
            }

            return new CourseResponse(TransactionStatus.SUCCESS,
                                        null,
                                        toRawCourse(course));

        } catch (CourseNotFoundException e) {
            return new CourseResponse(
                    TransactionStatus.NOT_FOUND,
                    COURSE_NOT_FOUND_ERROR_MESSAGE,
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

    public CoursesResponse getCoursesByTeacherId(UUID teacherId) {
        try {
            List<Course> courses = courseRepository.findByTeacherId(teacherId);

            if(courses.isEmpty()) {
                throw new CourseNotFoundException();
            }

            return new CoursesResponse(
                    TransactionStatus.SUCCESS,
                    null,
                    toCoursesWrapper(courses)
            );

        } catch (CourseNotFoundException e) {
            return new CoursesResponse(
                    TransactionStatus.NOT_FOUND,
                    COURSE_NOT_FOUND_ERROR_MESSAGE,
                    null
            );
        }
        catch (Exception e) {
            return new CoursesResponse(
                    TransactionStatus.INTERNAL_ERROR,
                    null,
                    null
            );
        }
    }

    public CoursesResponse getCoursesByStudentId(UUID studentId) {
        try {
            List<Course> courses = courseRepository.findByStudentId(studentId).orElse(null);

            assert courses != null;
            return new CoursesResponse(
                    TransactionStatus.SUCCESS,
                    null,
                    toCoursesWrapper(courses)
            );
        } catch (Exception e) {
            return new CoursesResponse(
                    TransactionStatus.INTERNAL_ERROR,
                    INTERNAL_ERROR_MESSAGE,
                    null);
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
                    INTERNAL_ERROR_MESSAGE,
                                        null);
        }
    }

    public CourseResponse addStudentToCourse(CourseAddStudentRequest request) {
        try {
            Course course = courseRepository.findById(request.courseId()).orElse(null);
            if (course == null) {
                throw new CourseNotFoundException(COURSE_NOT_FOUND_ERROR_MESSAGE);
            }

            course.getStudents().add(request.studentId());
            courseRepository.save(course);

            return new CourseResponse(
                    TransactionStatus.SUCCESS,
                    SUCCESS_MESSAGE,
                    null
            );
        } catch (CourseNotFoundException e) {
            return new CourseResponse(TransactionStatus.NOT_FOUND, e.getMessage(), null);
        } catch (Exception e) {
            return new CourseResponse(
                    TransactionStatus.INTERNAL_ERROR,
                    INTERNAL_ERROR_MESSAGE,
                    null
            );
        }
    }
}
