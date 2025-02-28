package com.tad.submission.service;

import com.tad.submission.model.Submission;
import com.tad.submission.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    public List<Submission> findAll() {
        return submissionRepository.findAll();
    }

    public Submission findById(UUID id) {
        return submissionRepository.findById(id).get();
    }

    public Submission save(Submission submission) {
        return submissionRepository.save(submission);
    }

    public void delete(Submission submission) {
        submissionRepository.delete(submission);
    }
}
