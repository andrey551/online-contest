package com.tad.problem.dto;

import java.util.List;
import java.util.UUID;

public record ListProblemsRequest (
        List<UUID> problemIDList
) {}
