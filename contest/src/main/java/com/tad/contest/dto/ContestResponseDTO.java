package com.tad.contest.dto;

import java.sql.Timestamp;

public record ContestResponseDTO(
        String title,
        String author,
        Timestamp startTime,
        Timestamp endTime
) {}
