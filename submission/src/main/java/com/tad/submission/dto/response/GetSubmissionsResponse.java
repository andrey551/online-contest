package com.tad.submission.dto.response;

import com.tad.submission.dto.wrapper.ShortenSubmissionsWrapper;
import org.springframework.http.HttpStatus;

public record GetSubmissionsResponse (
        HttpStatus status,
        String message,
        ShortenSubmissionsWrapper submissions
) {}
