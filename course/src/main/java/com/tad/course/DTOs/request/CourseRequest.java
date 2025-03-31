package com.tad.course.DTOs.request;

public record CourseRequest(
        String semester,
        String courseName,
        String description
) {}
