package com.tad.submission.model;

import com.tad.submission.constants.enums.SubmissionState;
import com.tad.submission.model.converters.TestResultConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "submission")
public class Submission implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    UUID id;

    @Column(name = "user_id")
    UUID userId;

    @Column(name = "laboratory_id")
    UUID laboratoryId;

    @Column(name = "download_link")
    String downloadLink;

    @Column(name = "submission_date")
    Timestamp submissionDate;

    @Column(name = "total_tests")
    Integer totalTests;

    @Column(name = "total_passed_tests")
    Integer totalPassedTests;

    @Convert(converter = TestResultConverter.class)
//    @Column(columnDefinition = "jsonb")
    @Column(name = "result")
    TestResult result;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "state")
    SubmissionState state;

    public void defineState() {
        if (result == null || result.getStatus_list() == null) {
            this.state = SubmissionState.WAITING;
            return;
        }

        // Handle empty status list
        if (result.getStatus_list().isEmpty()) {
            this.state = SubmissionState.RUNNING;
            return;
        }

        // Check for specific status conditions
        if (result.getStatus_list().contains("failed")) {
            this.state = SubmissionState.WRONG_ANSWER;
        }
        else if (result.getStatus_list().contains("memory_exceeded")) {
            this.state = SubmissionState.MEMORY_LIMIT_EXCEEDED;
        }
        else if (result.getStatus_list().contains("timed_out")) {
            this.state = SubmissionState.TIME_LIMIT_EXCEEDED;
        }
        else if (result.getStatus_list().stream().allMatch("passed"::equals)) {
            this.state = SubmissionState.ACCEPTED;
        }
        else {
            // If none of the above, but we have some statuses
            this.state = SubmissionState.RUNNING;
        }
    }
}
