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
}
