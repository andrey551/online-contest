package com.tad.submission.dto.raw;

import com.tad.submission.constants.enums.SubmissionState;

import java.sql.Timestamp;
import java.util.UUID;

public record ShortenSubmissionRaw(
        UUID submissionId,
        Timestamp submitTime,
        SubmissionState state
) {}
