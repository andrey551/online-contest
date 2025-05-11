package com.tad.submission.dto.request;

import java.util.UUID;

public record GetSubmissionsRequest(
        UUID userId,
        UUID laboratoryId
) {}
