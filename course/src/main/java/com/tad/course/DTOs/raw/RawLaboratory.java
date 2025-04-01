package com.tad.course.DTOs.raw;

import java.sql.Timestamp;
import java.util.List;

public record RawLaboratory(
        String title,
        String description,
        String tags,
        Timestamp createdTime,
        Timestamp deadline,
        String teacher

) {}
