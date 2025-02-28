package com.tad.user.dto;

import com.tad.user.model.enums.TransactionStatus;

public record WrapperResponse(
        TransactionStatus status,
        UserResponseDTO user
) { }
