package com.tad.submission.dto.wrapper;

import com.tad.submission.dto.raw.ShortenSubmissionRaw;

import java.util.List;

public record ShortenSubmissionsWrapper (
        List<ShortenSubmissionRaw> submissionRawList
) {}
