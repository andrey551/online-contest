package com.tad.problem.dto;

import com.tad.problem.model.Problem;
import com.tad.problem.model.enums.TransactionStatus;

import java.io.Serializable;

public record ProblemResponse (Problem problem,
                               TransactionStatus status)
        implements Serializable {}
