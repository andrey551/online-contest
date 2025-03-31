package com.tad.course.DTOs.raw;

public record RawCourse(
        String teacherName,
        String courseName,
        String description,
        String semester
) {}
