package com.tad.file.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class FileTaskService extends FileTaskServiceGrpc.FileTaskServiceImplBase {

    @Override
    public void handleFileTask(File.FileTaskRequest request,
                               StreamObserver<File.FileTaskResponse> responseObserver) {
        String taskType = request.getTaskType();
        String filename = request.getFilename();
        byte[] fileData = request.getFileData().toByteArray();
        String targetPath = request.getTargetPath();

        boolean success = true;

        String message = "OK";
        // "UPLOAD", "CREATE_DIR", "DELETE"
        try {
            switch (taskType) {
                case "UPLOAD":
                    break;
                case "CREATE_DIR":
                    break;
                case "DELETE":
                    break;
                default:
                    success = false;
                    message = "Invalid task type";
            }
        } catch (Exception e) {
            success = false;
            message = e.getMessage();
        }

        File.FileTaskResponse resp = File.FileTaskResponse
                                         .newBuilder()
                                         .setSuccess(success)
                                         .setMessage(message)
                                         .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }
}
