package com.tad.submission.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class TestResult {
    int total;
    int passed;
    List<String> status_list;
    List<Double> time_response;
    List<Long> memory_used;
}
