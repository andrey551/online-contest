package com.tad.file.service;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.storage.control.v2.*;
import com.tad.file.exceptions.CreateBlobException;
import com.tad.file.exceptions.CreateDirectoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

@Service
public class CloudStorageService {
    @Autowired
    private Environment environment;

    private final Storage storage;

    private final String bucketName = environment.getProperty("CLOUD_STORAGE_BUCKET");

    public CloudStorageService() {
        this.storage = StorageOptions.getDefaultInstance().getService();
    }

    public String createDirectory(String directory) {
        try (StorageControlClient storageControl = StorageControlClient.create()) {

            CreateFolderRequest request =
                    CreateFolderRequest.newBuilder()
                            .setParent(BucketName.format("_", bucketName))
                            .setFolderId(directory)
                            .build();

            Folder newFolder = storageControl.createFolder(request);

            return newFolder.getName();
        } catch (IOException e) {
            throw new CreateDirectoryException(e.toString());
        }

    }

    public String uploadFile(String directory, MultipartFile file) {
        try {
            String fileName = Paths.get(directory, file.getOriginalFilename()).toString();

            assert bucketName != null;
            BlobId blobId = BlobId.of(bucketName, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

            storage.create(blobInfo, file.getBytes());

            return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
        } catch (IOException e) {
            throw new CreateBlobException(e.toString());
        }
    }

    public void deleteFile(String fileName) {
        try (StorageControlClient storageControl = StorageControlClient.create()) {
//
//            // Set project to "_" to signify globally scoped bucket
//            String folderResourceName = FolderName.format("_", bucketName, folderName);
//            DeleteFolderRequest request =
//                    DeleteFolderRequest.newBuilder().setName(folderResourceName).build();
//
//            storageControl.deleteFolder(request);
//
//            System.out.printf("Deleted folder: %s%n", folderResourceName);
        } catch (IOException e) {
            throw new CreateBlobException(e.toString());
        }
    }
}
