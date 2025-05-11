package com.tad.submission.dto.response;

import com.tad.submission.dto.raw.DetailSubmissionRaw;
import org.springframework.http.HttpStatus;

public record GetDetailResponse(
        HttpStatus status,
        String message,
        DetailSubmissionRaw detail
) {}
