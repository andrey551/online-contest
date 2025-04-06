package com.tad.course.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message) {
        super(message);
    }
    public CourseNotFoundException() {};
}
