package com.tad.gateway.constants;

public enum ROLE {
    STUDENT("STUDENT"),
    TEACHER("TEACHER"),
    ADMIN("ADMIN");

    private String code;

    void ROLE(String code) {
        this.code = code;
    }

    ROLE(String code) {
        this.code = code;
    }
}
