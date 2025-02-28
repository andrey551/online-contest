package com.tad.problem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProblemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProblemApplication.class, args);
    }

}
