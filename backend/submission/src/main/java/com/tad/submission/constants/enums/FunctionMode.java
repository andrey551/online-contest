package com.tad.submission.constants.enums;

public enum FunctionMode {
    // "UPLOAD_LINK", "UPDATE_RESULT"
    UPLOAD_LINK("UPLOAD_LINK"),
    UPDATE_RESULT("UPDATE_RESULT");

    private String code;

    void FunctionMode(String code) {
        this.code = code;
    }

    FunctionMode(String code) {
        this.code = code;
    }
}
