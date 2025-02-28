package com.tad.problem.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ProblemDTO(
        @NotBlank String title,
        @NotBlank String description,
        @Nullable String author,
        @Nullable Integer difficulty,
        @Nullable List<String> tags
) {}
