package com.tad.problem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProblemApplication {
    public static final Logger logger = LoggerFactory.getLogger(ProblemApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(ProblemApplication.class, args);
    }

}
