package com.tad.submission.dto.request;

import java.util.UUID;

public record UpdateLinkRequest(
        UUID submissionId,
        String link
) {}
