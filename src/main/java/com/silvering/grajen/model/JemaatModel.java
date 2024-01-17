package com.silvering.grajen.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
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

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "ktp_id")
    private FileModel ktp;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "kk_id")
    private FileModel kk;
}
