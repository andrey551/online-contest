package com.tad.course.DTOs.request;

import java.util.UUID;

public record CourseAddStudentRequest(
        UUID studentId,
        UUID courseId
) {}
