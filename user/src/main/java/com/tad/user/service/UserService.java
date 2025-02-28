package com.tad.user.service;

import com.tad.user.dto.UserRequestDTO;
import com.tad.user.dto.UserResponseDTO;
import com.tad.user.dto.WrapperResponse;
import com.tad.user.mapper.UserMapper;
import com.tad.user.model.User;
import com.tad.user.model.enums.TransactionStatus;
import com.tad.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class UserService {

    private UserRepository userRepository;

    public WrapperResponse addUser(UserRequestDTO userDTO) {

        try {
            User userToSave = UserMapper.toEntity(userDTO);

            User user = userRepository.save(userToSave);

            return new WrapperResponse(TransactionStatus.SUCCESS, UserMapper.toDTO(user));
        } catch (Exception e) {
            return new WrapperResponse(TransactionStatus.SERVER_ERROR, null);
        }
    }

    public WrapperResponse updateUser(UserRequestDTO userDTO) {

        try {
            User user = userRepository.findByEmail(userDTO.email()).orElse(null);

            if(user == null)
                return new WrapperResponse(TransactionStatus.NOT_FOUND, null);

            user.setNickname(userDTO.nickname());
            user.setAvatar(userDTO.avatar());
            user.setFullName(userDTO.fullname());
            user.setOrganization(userDTO.organization());
            user.setUpdatedAt(Timestamp.from(Instant.now()));

            userRepository.save(user);

            return new WrapperResponse(TransactionStatus.SUCCESS, UserMapper.toDTO(user));
        } catch (Exception e) {
            return new WrapperResponse(TransactionStatus.SERVER_ERROR, null);
        }

    }

    public WrapperResponse deleteUser(String email) {
        try {
            userRepository.deleteUserByEmail(email);

            return new WrapperResponse(TransactionStatus.SUCCESS, null);
        } catch (Exception e) {
            return new WrapperResponse(TransactionStatus.SERVER_ERROR, null);
        }
    }

    public WrapperResponse getUser(String email) {
        try {
            User user = userRepository.findByEmail(email).orElse(null);
            if (user == null) {
                return new WrapperResponse(TransactionStatus.NOT_FOUND, null);
            }

            return new WrapperResponse(TransactionStatus.SUCCESS, UserMapper.toDTO(user));
        } catch (Exception e) {
            return new WrapperResponse(TransactionStatus.SERVER_ERROR, null);
        }
    }

    public WrapperResponse incAttempt(String email) {
        try {
            userRepository.incrementCounterByEmail(email);

            return new WrapperResponse(TransactionStatus.SUCCESS, null);
        } catch (Exception e) {
            return new WrapperResponse(TransactionStatus.SERVER_ERROR, null);
        }
    }
}
