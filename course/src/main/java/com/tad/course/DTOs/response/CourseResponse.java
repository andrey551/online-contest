package com.tad.course.DTOs.response;

import com.tad.course.constants.enums.TransactionStatus;
import com.tad.course.DTOs.raw.RawCourse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponse
        extends BaseResponse {
    private RawCourse rawCourse;

    public CourseResponse(TransactionStatus status, String message, RawCourse rawCourse) {
        super(status, message);
        this.rawCourse = rawCourse;
    }
}
