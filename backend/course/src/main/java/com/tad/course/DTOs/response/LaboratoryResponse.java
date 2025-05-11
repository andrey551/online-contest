package com.tad.course.DTOs.response;

import com.tad.course.constants.enums.TransactionStatus;
import com.tad.course.DTOs.raw.RawLaboratory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LaboratoryResponse
        extends BaseResponse {
    private RawLaboratory laboratory;

    public LaboratoryResponse(
            TransactionStatus status,
            String message,
            RawLaboratory laboratory) {
        super(status, message);
        this.laboratory = laboratory;
    }
}
