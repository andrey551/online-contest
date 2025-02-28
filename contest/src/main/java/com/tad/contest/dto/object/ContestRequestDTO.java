package com.tad.contest.dto.object;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public record ContestRequestDTO(
    UUID userId,
    String title,
    Timestamp startTime,
    Timestamp endTime,
    List<UUID> problem
) {}
