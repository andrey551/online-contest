package com.tad.course.mapper;

import com.tad.course.DTOs.response.CourseResponse;
import com.tad.course.DTOs.response.CoursesResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class HttpResponseMapper {
    public static ResponseEntity toHttpResponse(CourseResponse course) {
        return new ResponseEntity(
                course.getRawCourse(),
                HttpStatusCode.valueOf(course.getStatus().getCode()));
    }

    public static ResponseEntity toHttpResponse(CoursesResponse course) {
        return new ResponseEntity(
                course.getCourses(),
                HttpStatusCode.valueOf(course.getStatus().getCode()));
    }
}
