package com.tad.user.controller;

import com.tad.user.dto.UserRequestDTO;
import com.tad.user.dto.UserResponseDTO;
import com.tad.user.dto.WrapperResponse;
import com.tad.user.model.User;
import com.tad.user.service.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String email) {
        WrapperResponse response = userService.getUser(email);

        return new ResponseEntity<>(
                response.user(),
                HttpStatus.valueOf(response.status().getCode()));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable String email) {
        WrapperResponse response = userService.deleteUser(email);

        return new ResponseEntity<>(
                response.user(),
                HttpStatus.valueOf(response.status().getCode()));
    }

    @PostMapping("/")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
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

    @GetMapping("/inc-attempt/{email}")
    public ResponseEntity<UserResponseDTO> incAttempt( @PathVariable String email) {
        WrapperResponse response = userService.incAttempt(email);

        return new ResponseEntity<>(
                response.user(),
                HttpStatus.valueOf(response.status().getCode()));
    }

}
