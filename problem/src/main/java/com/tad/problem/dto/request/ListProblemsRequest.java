package com.tad.problem.dto.request;

import java.util.List;
import java.util.UUID;

public record ListProblemsRequest (
        List<UUID> problemIDList
) {}
