package com.silvering.grajen.service;

import com.silvering.grajen.model.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.Optional;

@Service
public class S3Service {
    private final S3Client s3Client;

    private final FileService fileService;

    @Value("${aws.bucketName}")
    private String bucketName;

    @Autowired
    public S3Service(S3Client s3Client, FileService fileService) {
        this.s3Client = s3Client;
        this.fileService = fileService;
    }

    // Check if file uploaded successfully
    public void uploadFile(MultipartFile file, Optional<String> path) {
        try {
            String file_path = path.orElse("") + file.getOriginalFilename();

            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(file_path)
                            .build(), RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
    }

    // Send message if the file deleted successfully
    public void deleteFile(String path) {
        try {
            s3Client.deleteObject(
                    DeleteObjectRequest.builder().
                            bucket(bucketName).
                            key(path).
                            build()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Failed to delete file", e);
        }
    }
}
