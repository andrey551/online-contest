package com.tad.user.service;

import com.tad.user.dto.UserRequestDTO;
import com.tad.user.dto.WrapperResponse;
import com.tad.user.mapper.UserMapper;
import com.tad.user.model.User;
import com.tad.user.constants.enums.TransactionStatus;
import com.tad.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static com.tad.user.UserApplication.logger;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public WrapperResponse addUser(UserRequestDTO userDTO) {

        try {
            User userToSave = UserMapper.toEntity(userDTO);
            userToSave.setTotalAttempt(0);

            User user = userRepository.save(userToSave);

            return new WrapperResponse(TransactionStatus.SUCCESS, UserMapper.toDTO(user));
        } catch (Exception e) {
            logger.error(e.getMessage());

            return new WrapperResponse(TransactionStatus.SERVER_ERROR, null);
        }
    }

    public WrapperResponse updateUser(UserRequestDTO userDTO) {

        try {
            User user = userRepository.findByEmail(userDTO.email()).orElse(null);

            if(user == null)
                return new WrapperResponse(TransactionStatus.NOT_FOUND, null);

            user.setNickname(userDTO.nickname());
            user.setFullName(userDTO.fullname());
            user.setOrganization(userDTO.organization());
            user.setUpdatedAt(Timestamp.from(Instant.now()));

            userRepository.save(user);

            return new WrapperResponse(TransactionStatus.SUCCESS, UserMapper.toDTO(user));
        } catch (Exception e) {
            return new WrapperResponse(TransactionStatus.SERVER_ERROR, null);
        }

    }

    public WrapperResponse deleteUser(UUID uuid) {
        try {
            userRepository.deleteById(uuid);

            return new WrapperResponse(TransactionStatus.SUCCESS, null);
        } catch (Exception e) {
            return new WrapperResponse(TransactionStatus.SERVER_ERROR, null);
        }
    }

    public WrapperResponse getUser(UUID uuid) {
        try {
            User user = userRepository.findUserById(uuid).orElse(null);
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
