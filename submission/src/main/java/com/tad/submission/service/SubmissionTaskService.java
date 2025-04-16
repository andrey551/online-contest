package com.tad.submission.service;

import com.tad.submission.constants.enums.FunctionMode;
import com.tad.submission.constants.enums.TransactionStatus;
import com.tad.submission.dto.request.UpdateLinkRequest;
import com.tad.submission.dto.request.UpdateResultRequest;
import com.tad.submission.grpc.Submission;
import com.tad.submission.model.TestResult;
import com.tad.submission.model.converters.TestResultConverter;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import com.tad.submission.grpc.SubmissionTaskServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Slf4j
@GrpcService
public class SubmissionTaskService
       extends SubmissionTaskServiceGrpc.SubmissionTaskServiceImplBase{
    private final SubmissionService submissionService;

    @Autowired
    public SubmissionTaskService(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @Override
    public void handleSubmissionTask(Submission.SubmissionTaskRequest request,
                                     StreamObserver<Submission.SubmissionTaskResponse> responseObserver) {
        FunctionMode taskType = FunctionMode.valueOf(request.getTaskType());
        String data = request.getData();
        UUID submissionId = UUID.fromString(request.getSubmissionId());
        TransactionStatus status = TransactionStatus.SUCCESS;
        switch (taskType) {
            case UPLOAD_LINK -> {
                UpdateLinkRequest updateLinkRequest = new UpdateLinkRequest(submissionId, data);
                status = submissionService.updateDownloadLink(updateLinkRequest);
            }
            case UPDATE_RESULT -> {
                TestResult testResult = new TestResultConverter().convertToEntityAttribute(data);
                UpdateResultRequest updateResultRequest = new UpdateResultRequest(submissionId, testResult);
                status = submissionService.updateResult(updateResultRequest);
                log.info(status.toString());
            }
            default -> log.error("Unsupported task type {}", taskType);
        }
        Submission.SubmissionTaskResponse resp = Submission.SubmissionTaskResponse
                .newBuilder()
                .setSuccess(status == TransactionStatus.SUCCESS)
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }

    @Override
    public void handleCreateSubmission(Submission.CreateSubmissionRequest request,
                                       StreamObserver<Submission.CreateSubmissionResponse> responseObserver) {
        UUID userId = UUID.fromString(request.getUserId());
        UUID laboratoryId = UUID.fromString(request.getLaboratoryId());

        UUID submissionId = submissionService.createSubmission(userId, laboratoryId);

        Submission.CreateSubmissionResponse resp = Submission.CreateSubmissionResponse
                .newBuilder()
                .setSubmissionId(submissionId.toString())
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }
}
