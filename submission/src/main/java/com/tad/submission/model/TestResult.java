package com.tad.submission.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestResult {
    List<String> status_list;
    List<Double> time_response;
    List<Long> memory_used;
}
