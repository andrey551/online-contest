package com.tad.course.DTOs.response;

import com.tad.course.DTOs.enums.TransactionStatus;
import com.tad.course.DTOs.wrapper.CoursesWrapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoursesResponse
        extends BaseResponse{
    private CoursesWrapper courses;

    public CoursesResponse(TransactionStatus status, String message, CoursesWrapper courses) {
        super(status, message);
        this.courses = courses;
    }
}
