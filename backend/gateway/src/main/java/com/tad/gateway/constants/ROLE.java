package com.tad.gateway.constants;

public enum ROLE {
    STUDENT("STUDENT"),
    TEACHER("TEACHER"),
    ADMIN("ADMIN"),
    UNAUTHORIZED("UNAUTHORIZED");

    private String code;

    void ROLE(String code) {
        this.code = code;
    }

    ROLE(String code) {
        this.code = code;
    }
}
