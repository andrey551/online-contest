package com.tad.course.DTOs.response;

import com.tad.course.DTOs.wrapper.LaboratoriesWrapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaboratoriesResponse
        extends BaseResponse {
    LaboratoriesWrapper laboratories;
}
