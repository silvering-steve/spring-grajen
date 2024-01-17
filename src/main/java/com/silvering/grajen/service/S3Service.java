//package com.silvering.grajen.service;
//
//import com.silvering.grajen.model.FileModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import software.amazon.awssdk.core.sync.RequestBody;
//import software.amazon.awssdk.services.s3.S3Client;
//import software.amazon.awssdk.services.s3.model.PutObjectRequest;
//
//import java.io.IOException;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.UUID;
//
//@Service
//public class S3Service {
//    private final S3Client s3Client;
//
//    @Value("${aws.bucketName}")
//    private String bucketName;
//
//    @Autowired
//    public S3Service(S3Client s3Client) {
//        this.s3Client = s3Client;
//    }
//
//    public FileModel uploadFile(MultipartFile file, Optional<String> path) {
//        try {
//            s3Client.putObject(
//                    PutObjectRequest.builder()
//                            .bucket(bucketName)
//                            .key(path.orElse("") + file.getOriginalFilename())
//                            .build(), RequestBody.fromInputStream(file.getInputStream(), file.getSize())
//            );
//
//            return new FileModel(
//                    UUID.randomUUID().toString(),
//                    Objects.requireNonNull(file.getOriginalFilename()).replaceAll("\\..*$", ""),
//                    path.orElse("") + file.getOriginalFilename()
//            );
//        } catch (IOException e) {
//            throw new IllegalStateException("Failed to upload file", e);
//        }
//    }
//}
