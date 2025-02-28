package com.tad.contest.dto.object;

import com.tad.contest.model.enums.ContestStatus;

import java.util.UUID;

public record ChangeStatusRequestDTO(
        UUID contestId,
        ContestStatus status
) {}
