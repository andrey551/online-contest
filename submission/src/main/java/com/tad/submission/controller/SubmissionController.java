package com.tad.submission.controller;

import com.tad.submission.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/submissions")
public class SubmissionController {
    @Autowired
    private SubmissionService submissionService;


}
