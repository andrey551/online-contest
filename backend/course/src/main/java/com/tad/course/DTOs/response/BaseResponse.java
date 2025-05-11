package com.tad.course.DTOs.response;


import com.tad.course.constants.enums.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseResponse {
    private TransactionStatus status;
    private String message;

    public BaseResponse(TransactionStatus status, String message) {
        this.status = status;
        this.message = message;
    }

}
