package com.tad.course.DTOs.response;

import com.tad.course.DTOs.enums.TransactionStatus;
import com.tad.course.DTOs.raw.RawCourse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponse
        extends BaseResponse {
    private RawCourse rawCourse;
}
