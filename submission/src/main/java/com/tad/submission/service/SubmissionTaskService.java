package com.tad.submission.service;


import com.tad.file.grpc.File;
import com.tad.submission.constants.enums.FunctionMode;
import com.tad.submission.grpc.Submission;
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
    @Autowired
    private SubmissionService submissionService;

    public void HandleSubmissionTask(Submission.SubmissionTaskRequest request,
                                     StreamObserver<Submission.SubmissionTaskResponse> responseObserver) {
        FunctionMode taskType = FunctionMode.valueOf(request.getTaskType());
        String data = request.getData();
        switch (taskType) {
            case UPLOAD_LINK -> {

            }
        }

//        responseObserver.onNext(resp);
//        responseObserver.onCompleted();
    }
    
    public void HandleCreateSubmission(Submission.CreateSubmissionRequest request,
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
