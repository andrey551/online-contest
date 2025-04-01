package com.tad.course.exceptions;

public class LaboratoryNotFound extends RuntimeException {
    public LaboratoryNotFound(String message) {
        super(message);
    }
    public LaboratoryNotFound() {}
}
