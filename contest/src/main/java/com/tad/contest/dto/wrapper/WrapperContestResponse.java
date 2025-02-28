package com.tad.contest.dto.wrapper;

import com.tad.contest.dto.object.ContestResponseDTO;
import com.tad.contest.model.enums.TransactionStatus;

public record WrapperContestResponse(
        TransactionStatus status,
        ContestResponseDTO response
) {}
