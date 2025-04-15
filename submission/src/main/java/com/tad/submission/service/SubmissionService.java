package com.tad.submission.service;

import com.tad.submission.constants.enums.SubmissionState;
import com.tad.submission.constants.enums.TransactionStatus;
import com.tad.submission.dto.raw.DetailSubmissionRaw;
import com.tad.submission.dto.raw.ShortenSubmissionRaw;
import com.tad.submission.dto.request.UpdateLinkRequest;
import com.tad.submission.dto.request.UpdateResultRequest;
import com.tad.submission.dto.response.GetDetailResponse;
import com.tad.submission.dto.response.GetSubmissionsResponse;
import com.tad.submission.dto.wrapper.ShortenSubmissionsWrapper;
import com.tad.submission.exceptions.NotFoundException;
import com.tad.submission.model.Submission;
import com.tad.submission.repository.SubmissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static com.tad.submission.constants.message.ResponseMessage.*;

@Service
@Slf4j
public class SubmissionService {
    private final SubmissionRepository submissionRepository;

    @Autowired
    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public UUID createSubmission(UUID userId, UUID laboratoryId) {
        try {
            Submission submission = Submission.builder()
                    .userId(userId)
                    .laboratoryId(laboratoryId)
                    .state(SubmissionState.WAITING)
                    .submissionDate(new Timestamp(System.currentTimeMillis()))
                    .build();
            Submission saved = submissionRepository.save(submission);
            return saved.getId();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }


    }

    public GetDetailResponse getDetail(UUID request) {
        try {
            DetailSubmissionRaw raw = submissionRepository.getDetailSubmission(request)
                                                          .orElseThrow(NotFoundException::new);

            return new GetDetailResponse(HttpStatus.OK, NULL, raw);
        } catch (NotFoundException e) {
            return new GetDetailResponse(HttpStatus.NOT_FOUND, NOT_FOUND, null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new GetDetailResponse(HttpStatus.SERVICE_UNAVAILABLE, ERROR, null);
        }
    }

    public GetSubmissionsResponse getSubmissions(UUID studentId, UUID laboratoryId) {
        try {
            List<ShortenSubmissionRaw> raw = submissionRepository.getShortenedSubmissions(studentId, laboratoryId)
                                                                 .orElseThrow(NotFoundException::new);

            return new GetSubmissionsResponse(HttpStatus.OK, NULL, new ShortenSubmissionsWrapper(raw));
        } catch (NotFoundException e) {
            return new GetSubmissionsResponse(HttpStatus.NOT_FOUND, NOT_FOUND, null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new GetSubmissionsResponse(HttpStatus.SERVICE_UNAVAILABLE, ERROR, null);
        }
    }

    public TransactionStatus updateDownloadLink(UpdateLinkRequest updateLinkRequest) {
        try {
            Submission submission = submissionRepository.getSubmissionById(updateLinkRequest.submissionId())
                                                        .orElseThrow(NotFoundException::new);

            submission.setDownloadLink(updateLinkRequest.link());
            submissionRepository.save(submission);

            return TransactionStatus.SUCCESS;
        } catch (NotFoundException e) {
            return TransactionStatus.NOT_FOUND;
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            return TransactionStatus.FAILURE;
        }
    }

    public TransactionStatus updateResult(UpdateResultRequest updateResultRequest) {
        try {
            Submission submission = submissionRepository.getSubmissionById(updateResultRequest.id())
                                                        .orElseThrow(NotFoundException::new);

            submission.setResult(updateResultRequest.result());
            submissionRepository.save(submission);

            return TransactionStatus.SUCCESS;
        } catch (NotFoundException e) {
            return TransactionStatus.NOT_FOUND;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return TransactionStatus.FAILURE;
        }
    }
}
