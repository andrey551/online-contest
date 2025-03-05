package com.tad.user.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UserResponseDTO (
        @NotBlank String uuid,
        @NotBlank String nickname,
        @NotBlank String fullname,
        @Nullable String organization,
        @Email @NotBlank @NotNull String email,
        @Size(min = 0) Integer totalAttempt
) {}
