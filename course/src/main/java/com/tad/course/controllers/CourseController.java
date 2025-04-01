package com.tad.course.controllers;

import com.tad.course.DTOs.request.CourseRequest;
import com.tad.course.DTOs.response.CourseResponse;
import com.tad.course.entities.Course;
import com.tad.course.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.tad.course.mapper.CourseMapper.toHttpResponse;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/")
    public ResponseEntity<Course> addCourse(@RequestBody CourseRequest course,
                                    @RequestHeader("user-id") UUID userId,
                                    @RequestHeader("user-name") String username) {

          CourseResponse response = courseService.insertCourse(course, username, userId);
          return toHttpResponse(response);
    }

    @GetMapping("/{course-id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable("course-id") UUID courseId) {
        CourseResponse response = courseService.getCourseById(courseId);
        return toHttpResponse(response);
    }



}
