package com.tad.file.constants;

import lombok.Getter;

@Getter
public enum FunctionMode {
    // "UPLOAD", "CREATE_DIR", "DELETE"
    UPLOAD("UPLOAD"),
    CREATE_DIR("CREATE_DIR"),
    DELETE("DELETE");

    private String code;

    void FunctionMode(String code) {
        this.code = code;
    }

    FunctionMode(String code) {
        this.code = code;
    }
}
