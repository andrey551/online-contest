package com.tad.course.DTOs.response;

import com.tad.course.constants.enums.TransactionStatus;
import com.tad.course.DTOs.wrapper.LaboratoriesWrapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaboratoriesResponse
        extends BaseResponse {
    LaboratoriesWrapper laboratories;

    public LaboratoriesResponse(
            TransactionStatus status,
            String message,
            LaboratoriesWrapper laboratories) {
        super(status, message);
        this.laboratories = laboratories;
    }
}
