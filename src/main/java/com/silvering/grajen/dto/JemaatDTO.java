package com.silvering.grajen.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

import java.util.Date;

@Data
public class JemaatDTO {
    @NotNull(message = "Member number can't be empty")
    @Size(min = 3, max = 50, message = "Member number must be 3 to 50 chars")
    private String memberNumber;

    @NotNull(message = "NIK can't be empty")
    @Size(min = 16, max = 16, message = "NIK must be 16 characters")
    @Pattern(regexp = "[0-9]+", message = "Nik must be a number")
    private String nik;

    @NotNull(message = "Name can't be empty")
    @Size(min = 3, max = 50, message = "Name must be 3 to 50 chars")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Name must be a string")
    private String name;

    @NotNull(message = "Birthplace can't be empty")
    @Size(min = 3, max = 50, message = "Birthplace must be 3 to 50 chars")
    private String birthplace;

    @NotNull(message = "Birthdate can't be empty")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthdate;

    @NotBlank(message = "Religion can't be empty")
    @Size(min = 3, max = 50, message = "Religion must be 3 to 50 chars")
    private String religion;
}
