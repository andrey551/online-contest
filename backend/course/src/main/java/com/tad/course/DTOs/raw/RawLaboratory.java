package com.tad.course.DTOs.raw;

import java.sql.Timestamp;

public record RawLaboratory(
        String id,
        String title,
        String description,
        String tags,
        Timestamp createdTime,
        Timestamp deadline
) {
    public RawLaboratory {
        if(createdTime == null) {
            createdTime = new Timestamp(System.currentTimeMillis());
        }
    }
}
