package com.tad.file.service;

import com.tad.file.constants.FunctionMode;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.tad.file.grpc.FileTaskServiceGrpc;
import com.tad.file.grpc.File;

import static com.tad.file.constants.ExceptionMessage.FUNCTION_MODE_NOT_AVAILABLE;
import static com.tad.file.constants.FileFormat.ZIP;
import static com.tad.file.constants.ReturnMessage.OK;
import static com.tad.file.utils.FileConverter.convertBytesToMultipartFile;

@Slf4j
@GrpcService
public class FileTaskService
       extends FileTaskServiceGrpc.FileTaskServiceImplBase {

    @Autowired
    private CloudStorageService cloudStorageService;

    @Override
    public void handleFileTask(File.FileTaskRequest request,
                               StreamObserver<File.FileTaskResponse> responseObserver) {
        FunctionMode taskType = FunctionMode.valueOf(request.getTaskType());
        String filename = request.getFilename();
        byte[] fileData = request.getFileData().toByteArray();
        String targetPath = request.getTargetPath();
        MultipartFile file = convertBytesToMultipartFile(fileData,filename, filename, ZIP);

//       Log info
        log.info(String.format("Task: %s %s %s", filename, targetPath, taskType));
        boolean success = true;

        String message = OK;

        try {
            switch (taskType) {
                case UPLOAD:
                    message = cloudStorageService.uploadFile(targetPath, file);
                    break;
                case CREATE_DIR:
                    message = cloudStorageService.createDirectory(targetPath);
                    break;
                case DELETE:
                    cloudStorageService.deleteFile(targetPath);
                    break;
                default:
                    success = false;
                    message = FUNCTION_MODE_NOT_AVAILABLE;
            }
        } catch (Exception e) {
            success = false;
            message = e.getMessage();
        }

        log.info("Message: " + message);

        File.FileTaskResponse resp = File.FileTaskResponse
                                         .newBuilder()
                                         .setSuccess(success)
                                         .setMessage(message)
                                         .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }
}
