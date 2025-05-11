package com.tad.user.dto;

import com.tad.user.constants.enums.TransactionStatus;

public record WrapperResponse(
        TransactionStatus status,
        UserResponseDTO user
) { }
