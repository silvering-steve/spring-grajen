package com.silvering.grajen.controllers;

import com.silvering.grajen.model.KTPModel;
import com.silvering.grajen.service.TextractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public KTPModel extractKTPData(
            @RequestParam(value = "name") String name
    ) {
        try {
            return textractService.extractDataS3(name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
