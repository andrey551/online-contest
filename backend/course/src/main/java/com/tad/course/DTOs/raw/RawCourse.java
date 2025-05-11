package com.tad.course.DTOs.raw;

import java.util.List;

public record RawCourse(
        String id,
        String teacherName,
        String courseName,
        String description,
        String semester,
        List<String> laboratories
) {}
