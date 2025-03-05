package com.tad.user.controller;

import com.tad.user.dto.UserRequestDTO;
import com.tad.user.dto.UserResponseDTO;
import com.tad.user.dto.WrapperResponse;
import com.tad.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.tad.user.UserApplication.logger;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String uuid) {
        WrapperResponse response = userService.getUser(uuid);

        return new ResponseEntity<>(
                response.user(),
                HttpStatus.valueOf(response.status().getCode()));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable String uuid) {
        WrapperResponse response = userService.deleteUser(uuid);

        return new ResponseEntity<>(
                response.user(),
                HttpStatus.valueOf(response.status().getCode()));
    }

    @PostMapping("/")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        logger.info(userRequestDTO.toString());
        WrapperResponse response = userService.addUser(userRequestDTO);

        return new ResponseEntity<>(
                response.user(),
                HttpStatus.valueOf(response.status().getCode()));
    }

    @PutMapping("/")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        WrapperResponse response = userService.updateUser(userRequestDTO);

        return new ResponseEntity<>(
                response.user(),
                HttpStatus.valueOf(response.status().getCode()));
    }

    @GetMapping("/inc-attempt/{uuid}")
    public ResponseEntity<UserResponseDTO> incAttempt( @PathVariable String uuid) {
        WrapperResponse response = userService.incAttempt(uuid);

        return new ResponseEntity<>(
                response.user(),
                HttpStatus.valueOf(response.status().getCode()));
    }

}
