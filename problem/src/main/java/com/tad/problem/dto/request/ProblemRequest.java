package com.tad.problem.dto.request;

import com.tad.problem.model.enums.Tag;

import java.util.List;

public record ProblemRequest(
        String title,
        String description,
        String author,
        Integer difficulty,
        List<Tag> tags
) {}
