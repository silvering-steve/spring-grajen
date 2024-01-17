package com.silvering.grajen.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "files")
public class FileModel {
    @Id
    private long id;

    @NotNull(message = "File path can't be empty")
    @Column(name = "path")
    private String path;

    @Column(name = "uploaded_at")
    private Date uploadedAt;

}
