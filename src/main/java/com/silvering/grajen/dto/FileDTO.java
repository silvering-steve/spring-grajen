package com.silvering.grajen.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileDTO {
    @NotNull(message = "File path can't be empty")
    private String path;

    private LocalDateTime uploadedAt;
}
