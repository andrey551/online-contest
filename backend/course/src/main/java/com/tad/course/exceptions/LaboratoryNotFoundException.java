package com.tad.course.exceptions;

public class LaboratoryNotFoundException extends RuntimeException {
    public LaboratoryNotFoundException(String message) {
        super(message);
    }
    public LaboratoryNotFoundException() {}
}
