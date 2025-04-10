package com.tad.submission.repository;

import com.tad.submission.dto.raw.DetailSubmissionRaw;
import com.tad.submission.dto.raw.ShortenSubmissionRaw;
import com.tad.submission.model.Submission;
import com.tad.submission.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubmissionRepository
        extends JpaRepository<Submission, UUID> {
    @Query("SELECT new com.tad.submission.dto.raw.ShortenSubmissionRaw(i.id, i.submissionDate, i.state) " +
            "FROM Submission i WHERE i.userId = :userId AND i.laboratoryId = :laboratoryId")
    Optional<List<ShortenSubmissionRaw>> getShortenedSubmissions(UUID userId, UUID laboratoryId);

    @Query("SELECT new com.tad.submission.dto.raw.DetailSubmissionRaw" +
            "(i.downloadLink, i.submissionDate, i.totalTests,i.totalPassedTests, i.result) " +
            "from Submission i where i.id = :submissionId")
    Optional<DetailSubmissionRaw> getDetailSubmission(UUID submissionId);

    void updateDownloadLinkById(UUID id, String downloadLink);

    Optional<Submission> getSubmissionById(UUID id);

}
