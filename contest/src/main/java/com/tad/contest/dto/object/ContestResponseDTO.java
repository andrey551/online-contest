package com.tad.contest.dto.object;

import java.sql.Timestamp;

public record ContestResponseDTO(
        String title,
        String author,
        Timestamp startTime,
        Timestamp endTime
) {}
