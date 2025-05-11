package com.tad.course.DTOs.request;

import java.sql.Timestamp;
import java.util.UUID;

public record LaboratoryRequest(
   UUID courseId,
   String title,
   String tags,
   Timestamp deadline,
   String description
) {}
