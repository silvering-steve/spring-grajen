package com.silvering.grajen.controllers;

import com.silvering.grajen.model.FileModel;
import com.silvering.grajen.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/bucket")
public class S3Controller {
    private final S3Service s3Service;

    @Autowired
    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public FileModel uploadFile(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "path") Optional<String> path
    ) {
        return s3Service.uploadFile(file, path);
    }
}
