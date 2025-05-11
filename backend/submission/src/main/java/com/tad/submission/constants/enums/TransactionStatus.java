package com.tad.submission.constants.enums;

import lombok.Getter;

@Getter
public enum TransactionStatus {
    SUCCESS(200),
    FAILURE(400),
    NOT_FOUND(404),
    INTERNAL_ERROR(500);

    // Method to get the numeric value
    private int code; // Numeric value associated with the enum

    // Constructor to initialize the numeric value
    void Status(int code) {
        this.code = code;
    }

    TransactionStatus(int code) {
        this.code = code;
    }
}
