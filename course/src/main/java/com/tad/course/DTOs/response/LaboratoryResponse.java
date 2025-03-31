package com.tad.course.DTOs.response;

import com.tad.course.DTOs.raw.RawLaboratory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaboratoryResponse
        extends BaseResponse {
    private RawLaboratory laboratory;
}
