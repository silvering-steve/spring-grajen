package com.silvering.grajen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "umat")
public class UmatModel {
    /**
     * The id of the umat.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The member number of the umat.
     */
    @NotNull(message = "Member number can't be empty")
    @Size(min = 3, max = 50, message = "Member number must be 3 to 50 chars")
    @Column(name = "member_number")
    private String memberNumber;

    /**
     * The NIK number of the umat.
     */
    @NotNull(message = "NIK can't be empty")
    @Size(min = 16, max = 16, message = "NIK must be 16 characters")
    @Pattern(regexp = "[0-9]+", message = "Nik must be a number")
    @Column(name = "nik")
    private String nik;

    /**
     * The name of the umat.
     */
    @NotNull(message = "Name can't be empty")
    @Size(min = 3, max = 50, message = "Name must be 3 to 50 chars")
    @Pattern(regexp = "[a-zA-Z\\s]+", message = "Name must be a string")
    @Column(name = "name")
    private String name;

    /**
     * The birthplace of the umat.
     */
    @NotNull(message = "Birthplace can't be empty")
    @Size(min = 3, max = 50, message = "Birthplace must be 3 to 50 chars")
    @Column(name = "birthplace")
    private String birthplace;

    /**
     * The birthdate of the umat.
     */
    @NotNull(message = "Birthdate can't be empty")
    @Size(min = 3, max = 50, message = "Birthdate must be 3 to 50 chars")
    @Column(name = "birthdate")
    private String birthdate;

    /**
     * The religion of the umat.
     */
    @NotBlank(message = "Religion can't be empty")
    @Size(min = 3, max = 50, message = "Religion must be 3 to 50 chars")
    @Column(name = "religion")
    private String religion;

    /**
     * The main constructor for the umat model.
     */
    public UmatModel() {

    }

    /**
     * The constructor for the umat model.
     *
     * @param memberNumber The member number.
     * @param nik           The NIK number of the umat.
     * @param name          The name of the umat.
     * @param birthplace    The birthplace of the umat.
     * @param birthdate     The birthdate of the umat.
     * @param religion      The religion of the umat.
     */
    public UmatModel(String memberNumber, String nik, String name, String birthplace, String birthdate, String religion) {
        this.memberNumber = memberNumber;
        this.nik = nik;
        this.name = name;
        this.birthplace = birthplace;
        this.birthdate = birthdate;
        this.religion = religion;
    }

    /**
     * Get the unique identifier of the UmatModel.
     *
     * @return The unique identifier.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the unique identifier of the UmatModel.
     *
     * @param id The unique identifier.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the member number of the UmatModel.
     *
     * @return The member number.
     */
    public String getMemberNumber() {
        return memberNumber;
    }

    /**
     * Set the member number of the UmatModel.
     *
     * @param memberNumber The member number.
     */
    public void setMemberNumber(String memberNumber) {
        this.memberNumber = memberNumber;
    }

    /**
     * Get the NIK number of the UmatModel.
     *
     * @return The NIK number.
     */
    public String getNik() {
        return nik;
    }

    /**
     * Set the NIK number of the UmatModel.
     *
     * @param nik The NIK number.
     */
    public void setNik(String nik) {
        this.nik = nik;
    }

    /**
     * Get the name of the UmatModel.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the UmatModel.
     *
     * @param name The name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the birthplace of the UmatModel.
     *
     * @return The birthplace.
     */
    public String getBirthplace() {
        return birthplace;
    }

    /**
     * Set the birthplace of the UmatModel.
     *
     * @param birthplace The birthplace.
     */
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    /**
     * Get the birthdate of the UmatModel.
     *
     * @return The birthdate.
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * Set the birthdate of the UmatModel.
     *
     * @param birthdate The birthdate.
     */
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Get the religion of the UmatModel.
     *
     * @return The religion.
     */
    public String getReligion() {
        return religion;
    }

    /**
     * Set the religion of the UmatModel.
     *
     * @param religion The religion.
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }
}
