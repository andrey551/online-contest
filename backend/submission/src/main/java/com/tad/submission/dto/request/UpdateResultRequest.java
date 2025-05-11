package com.tad.submission.dto.request;

import com.tad.submission.model.TestResult;

import java.util.UUID;

public record UpdateResultRequest (
        UUID id,
        TestResult result
) {}
