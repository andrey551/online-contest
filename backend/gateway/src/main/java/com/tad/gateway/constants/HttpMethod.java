package com.tad.gateway.constants;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE");

    private String code;

    void HttpMethod(String code) {
        this.code = code;
    }

    HttpMethod(String code) {
        this.code = code;
    }
}
