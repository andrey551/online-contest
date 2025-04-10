package com.tad.submission.service;

import com.tad.submission.constants.enums.TransactionStatus;
import com.tad.submission.dto.raw.DetailSubmissionRaw;
import com.tad.submission.dto.raw.ShortenSubmissionRaw;
import com.tad.submission.dto.request.GetDetailRequest;
import com.tad.submission.dto.request.GetSubmissionsRequest;
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

import java.util.List;

import static com.tad.submission.constants.message.ResponseMessage.*;

@Service
@Slf4j
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    public GetDetailResponse getDetail(GetDetailRequest request) {
        try {
            DetailSubmissionRaw raw = submissionRepository.getDetailSubmission(request.laboratoryId())
                                                          .orElseThrow(NotFoundException::new);

            return new GetDetailResponse(HttpStatus.OK, NULL, raw);
        } catch (NotFoundException e) {
            return new GetDetailResponse(HttpStatus.NOT_FOUND, NOT_FOUND, null);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new GetDetailResponse(HttpStatus.SERVICE_UNAVAILABLE, ERROR, null);
        }
    }

    public GetSubmissionsResponse getSubmissions(GetSubmissionsRequest request) {
        try {
            List<ShortenSubmissionRaw> raw = submissionRepository.getShortenedSubmissions(request.userId(),
                                                                                          request.laboratoryId())
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
            submissionRepository.updateDownloadLinkById(updateLinkRequest.submissionId(),
                                                        updateLinkRequest.link());

            return TransactionStatus.SUCCESS;
        } catch (Exception e) {
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
