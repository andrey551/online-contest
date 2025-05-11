package com.tad.submission.controller;

import com.tad.submission.dto.response.GetDetailResponse;
import com.tad.submission.dto.response.GetSubmissionsResponse;
import com.tad.submission.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.tad.submission.mapper.HttpResponseMapper.toHttpResponse;

@RestController
@RequestMapping(path = "api/v1/submissions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SubmissionController {
    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<GetDetailResponse> getDetail(@PathVariable("id") UUID id) {
        GetDetailResponse response = submissionService.getDetail(id);
        return toHttpResponse(response);
    }

    @GetMapping("/student/{student-id}/laboratory/{laboratory-id}")
    public ResponseEntity<GetSubmissionsResponse> getSubmissions(@PathVariable("student-id") UUID studentId,
                                                                 @PathVariable("laboratory-id") UUID laboratoryId) {
        GetSubmissionsResponse response = submissionService.getSubmissions(studentId, laboratoryId);
        return toHttpResponse(response);
    }


}
