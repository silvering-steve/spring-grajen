package com.silvering.grajen.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "files")
public class FileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "File path can't be empty")
    @Column(name = "path")
    private String path;

    @Column(name = "uploaded_at")
    private LocalDateTime uploadedAt;
}
