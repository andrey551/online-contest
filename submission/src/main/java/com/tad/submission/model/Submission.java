package com.tad.submission.model;

import com.tad.submission.model.enums.SubmissionState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "submission")
public class Submission {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "problem_id")
    private UUID problemId;

    @Column(name = "submission_date")
    private Timestamp submissionDate;

    @Column(name = "total_tests")
    private Integer totalTests;

    @Column(name = "total_passed_tests")
    private Integer totalPassedTests;

    private SubmissionState state;
}
