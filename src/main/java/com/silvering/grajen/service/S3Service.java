package com.silvering.grajen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.Optional;

@Service
public class S3Service {
    private final S3Client s3Client;

    @Value("${aws.bucketName}")
    private String bucketName;

    @Autowired
    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public PutObjectResponse uploadFile(MultipartFile file, Optional<String> path) {
        try {
            return s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(path.orElse("") + file.getOriginalFilename())
                            .build(), RequestBody.fromInputStream(file.getInputStream(), file.getSize())
                    );
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
    }
}
