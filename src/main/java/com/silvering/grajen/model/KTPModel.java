package com.silvering.grajen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * The ktp model that consist of id and the file path in s3.
 */
@Entity
@Table(name = "ktp")
public class KTPModel {
    /**
     * The id of the ktp.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The file path in s3 bucket
     */
    @NotBlank(message = "Path is can't be blank")
    @Column(name = "path")
    private String path;

    /**
     * Empty constructor with default value
     */
    public KTPModel() {

    }

    /**
     * The constructor for the ktp model.
     *
     * @param path the file path in s3 bucket.
     */
    public KTPModel(String path) {
        this.path = path;
    }

    /**
     * Get the id.
     *
     * @return The ktp id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id.
     *
     * @param id The id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the ktp path in s3.
     *
     * @return The file path in s3.
     */
    public String getPath() {
        return path;
    }

    /**
     * Set the ktp path in s3.
     *
     * @param path The new file path in s3.
     */
    public void setPath(String path) {
        this.path = path;
    }
}
