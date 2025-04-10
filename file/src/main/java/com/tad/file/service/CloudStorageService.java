package com.tad.file.service;

import com.google.cloud.storage.*;
import com.google.storage.control.v2.*;
import com.tad.file.exceptions.CreateBlobException;
import com.tad.file.exceptions.CreateDirectoryException;
import com.tad.file.exceptions.FileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

import static com.tad.file.constants.ExceptionMessage.FILE_NOT_FOUND_MESSAGE;

@Slf4j
@Service
public class CloudStorageService {
    private final Storage storage;

    @Value("${cloud.storage.bucket}")
    private String bucketName;

    public CloudStorageService() {
        this.storage = StorageOptions.getDefaultInstance().getService();
    }

    public String createDirectory(String directory) {
        try (StorageControlClient storageControl = StorageControlClient.create()) {
            CreateFolderRequest request =
                    CreateFolderRequest.newBuilder()
                            .setParent(bucketName)
                            .setFolderId(directory)
                            .build();
            Folder newFolder = storageControl.createFolder(request);
            log.info("Creating folder {}", newFolder.getName());
            return newFolder.getName();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new CreateDirectoryException(e.toString());
        } catch ( Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public String uploadFile(String directory, MultipartFile file) {
        try {
            String fileName = Paths.get(directory, file.getOriginalFilename()).toString();

            assert bucketName != null;
            BlobId blobId = BlobId.of(bucketName, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

            storage.create(blobInfo, file.getBytes());

            return String.format("https://storage.googleapis.com/%s/%s/%s", bucketName, directory, fileName);
        } catch (IOException e) {
            throw new CreateBlobException(e.toString());
        }
    }

    public void deleteFile(String fileName) {
        try (StorageControlClient storageControl = StorageControlClient.create()) {
            Blob blob = storage.get(bucketName, fileName);
            if (blob == null) {
                throw new FileNotFoundException(FILE_NOT_FOUND_MESSAGE);
            }
            BlobId idWithGeneration = blob.getBlobId();
            // Deletes the blob specified by its id. When the generation is present and non-null it will be
            // specified in the request.
            // If versioning is enabled on the bucket and the generation is present in the delete request,
            // only the version of the object with the matching generation will be deleted.
            // If instead you want to delete the current version, the generation should be dropped by
            // performing the following.
            // BlobId idWithoutGeneration =
            //    BlobId.of(idWithGeneration.getBucket(), idWithGeneration.getName());
            // storage.delete(idWithoutGeneration);
            storage.delete(idWithGeneration);
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            throw new CreateBlobException(e.toString());
        }
    }
}
