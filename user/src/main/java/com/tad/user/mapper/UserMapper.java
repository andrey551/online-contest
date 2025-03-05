package com.tad.user.mapper;

import com.tad.user.dto.UserRequestDTO;
import com.tad.user.dto.UserResponseDTO;
import com.tad.user.model.User;

import java.sql.Timestamp;
import java.time.Instant;

public class UserMapper {
    public static User toEntity(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setOrganization(user.getOrganization());
        user.setNickname(userRequestDTO.nickname());
        user.setEmail(userRequestDTO.email());
        user.setFullName(userRequestDTO.fullname());
        user.setCreatedAt(Timestamp.from(Instant.now()));
        user.setUpdatedAt(Timestamp.from(Instant.now()));
        user.setLastLogin(Timestamp.from(Instant.now()));
        return user;
    }

    public static UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
                user.getUuid(),
                user.getNickname(),
                user.getFullName(),
                user.getOrganization(),
                user.getEmail(),
                user.getTotalAttempt()
        );
    }

    public static User updateEntity(User user, UserRequestDTO userRequestDTO) {
        user.setNickname(userRequestDTO.nickname());
        user.setEmail(userRequestDTO.email());
        user.setFullName(userRequestDTO.fullname());
        user.setUpdatedAt(Timestamp.from(Instant.now()));
        return user;
    }
}
