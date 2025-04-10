package com.tad.submission.service;


import com.tad.submission.constants.enums.FunctionMode;
import com.tad.submission.grpc.Submission;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import com.tad.submission.grpc.SubmissionTaskServiceGrpc;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@GrpcService
public class SubmissionTaskService
       extends SubmissionTaskServiceGrpc.SubmissionTaskServiceImplBase{
    @Autowired
    private SubmissionService submissionService;

    @Override
    public void HandleSubmissionTask(Submission.SubmissionTaskRequest request) {
        FunctionMode taskType = FunctionMode.valueOf(request.getTaskType());
        String data = request.getData();
        switch (taskType) {
            case UPLOAD_LINK -> {

            }
        }
    }
}
