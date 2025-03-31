package com.tad.course.DTOs.response;

import com.tad.course.DTOs.wrapper.CoursesWrapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoursesResponse
        extends BaseResponse{
    private CoursesWrapper courses;
}
