package com.tad.contest.dto.wrapper;

import java.util.List;

import com.tad.contest.model.enums.TransactionStatus;

public record WrapperContestsName(
        TransactionStatus status,
        List<String> contestsName
) {}
