package com.silvering.grajen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jemaat")
public class JemaatModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberNumber;

    private String nik;

    private String name;

    private String birthplace;

    private Date birthdate;

    private String religion;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ktp_id")
    private FileModel ktp;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kk_id")
    private FileModel kk;
}
