package com.tad.course.exceptions;

public class CourseNotFound extends RuntimeException {
    public CourseNotFound(String message) {
        super(message);
    }
    public CourseNotFound() {};
}
