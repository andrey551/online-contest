package com.tad.submission.dto.raw;

import com.tad.submission.model.TestResult;

import java.sql.Timestamp;

public record DetailSubmissionRaw(
   String downloadLink,
   Timestamp submissionDate,
   Integer totalTests,
   Integer totalPassedTests,
   TestResult results
) {}
