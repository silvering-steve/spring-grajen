package com.silvering.grajen.controllers;

import com.silvering.grajen.model.KTPModel;
import com.silvering.grajen.service.TextractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/v1/ocr")
public class TextractController {
    private final TextractService textractService;

    @Autowired
    public TextractController(TextractService textractService) {
        this.textractService = textractService;
    }

    @PostMapping("/ktp")
    public ResponseEntity<KTPModel> extractKTPData(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "name", required = false) String name
    ) {
        try {
            if (name != null && file != null) throw new RuntimeException("There only can be 1 params the need to be filled");

            if (name != null) return ResponseEntity.ok(textractService.extractDataFromS3(name));

            if (file != null) return ResponseEntity.ok(textractService.extractDataFromImage(file));

            throw new RuntimeException("File and file name is empty");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
