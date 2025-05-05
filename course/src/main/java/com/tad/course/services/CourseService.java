package com.tad.course.services;


import com.google.protobuf.ByteString;
import com.tad.course.DTOs.request.CourseAddStudentRequest;
import com.tad.course.constants.enums.TransactionStatus;
import com.tad.course.DTOs.request.CourseRequest;
import com.tad.course.DTOs.response.CourseResponse;
import com.tad.course.DTOs.response.CoursesResponse;
import com.tad.course.entities.Course;
import com.tad.course.exceptions.CourseNotFoundException;
import com.tad.course.repositories.CourseRepository;
import com.tad.file.grpc.File;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.tad.course.constants.FunctionMode.CREATE_DIR_MODE;
import static com.tad.course.constants.TransactionMessage.*;
import static com.tad.course.mapper.CourseMapper.*;

@Slf4j
@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final FileTaskGrpcClient client;

    @Autowired
    public CourseService(CourseRepository courseRepository,
                         FileTaskGrpcClient client) {
        this.courseRepository = courseRepository;
        this.client = client;
    }

    public CourseResponse getCourseById(UUID id) {
        try {
            Course course = courseRepository.findById(id)
                                            .orElseThrow(CourseNotFoundException::new);

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
            List<Course> courses = courseRepository.findByTeacherId(teacherId)
                                                   .orElseThrow(CourseNotFoundException::new);

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
            List<Course> courses = courseRepository.findByStudentId(studentId)
                                                   .orElse(null);

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

//            File.FileTaskResponse response = client.sendFileTask(ByteString.EMPTY.toByteArray(),
//                                                                "",
//                                                                CREATE_DIR_MODE,
//                                                                course.getTitle());
//
//            if(response.getSuccess()) {
//                course.setDirectory(response.getMessage());
//            } else {
//                log.info("Can't create dir on cloud");
//            }

            Course courseSaved = courseRepository.save(course);

            return new CourseResponse(TransactionStatus.SUCCESS,
                    null,
                    toRawCourse(courseSaved));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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
