package com.tad.problem.dto.response;

import com.tad.problem.model.Problem;
import com.tad.problem.model.enums.TransactionStatus;

import java.util.List;

public record ProblemsResponse(TransactionStatus status,
                               List<Problem> problems)
{}
