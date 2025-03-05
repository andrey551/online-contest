package com.tad.user;

import com.tad.user.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {
    public static final Logger logger = LoggerFactory.getLogger(UserApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
