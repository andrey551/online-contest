package com.tad.contest.dto.wrapper;

import java.util.List;

import com.tad.contest.dto.object.ContestResponseDTO;
import com.tad.contest.model.enums.TransactionStatus;

public record WrapperContestsResponse (
   TransactionStatus status,
   List<ContestResponseDTO> contests
) {}
