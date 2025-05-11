package com.tad.course.services;

import com.tad.file.grpc.File;
import com.tad.file.grpc.FileTaskServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileTaskGrpcClient {
    private final ManagedChannel channel;
    private final FileTaskServiceGrpc.FileTaskServiceBlockingStub stub;

    public FileTaskGrpcClient(@Value("${grpc.info.host}") String grpcHost,
                              @Value("${grpc.info.port}") int grpcPort ) {
        log.info("grpc info: " + grpcHost + ":" + grpcPort);
        this.channel = ManagedChannelBuilder
                .forAddress(grpcHost, grpcPort)
                .usePlaintext()
                .build();

        this.stub = FileTaskServiceGrpc.newBlockingStub(channel);
    }

    public File.FileTaskResponse sendFileTask(byte[] fileData, String filename, String taskType, String targetPath) {
        File.FileTaskRequest request = File.FileTaskRequest.newBuilder()
                .setFileData(com.google.protobuf.ByteString.copyFrom(fileData))
                .setFilename(filename)
                .setTaskType(taskType)
                .setTargetPath(targetPath)
                .build();

        return stub.handleFileTask(request);
    }

    @PreDestroy
    public void shutdown() {
        channel.shutdown();
    }
}
