package com.silvering.grajen.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KTPModel {
    /**
     * The name of the person.
     */
    @JsonProperty("Nama")
    private String name;

    /**
     * The National Identification Number (NIK) of the person.
     */
    @JsonProperty("NIK")
    private String nik;

    /**
     * The religion of the person.
     */
    @JsonProperty("Agama")
    private String religion;

    /**
     * The gender or sex of the person.
     */
    @JsonProperty("Jeniskelamin")
    private String sex;

    /**
     * The citizenship status of the person.
     */
    @JsonProperty("Kewarganegaraan")
    private String citizenship;

    /**
     * Information about the birth of the person.
     */
    @JsonProperty("TempatTglLahir")
    private String birthInfo;

    /**
     * The person job.
     */
    @JsonProperty("Pekerjaan")
    private String job;

    /**
     * The address of the person.
     */
    @JsonProperty("Alamat")
    private String address;

    /**
     * Gets the name of the person.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the National Identification Number (NIK) of the person.
     *
     * @return The NIK.
     */
    public String getNik() {
        return nik;
    }

    /**
     * Sets the National Identification Number (NIK) of the person.
     *
     * @param nik The NIK to set.
     */
    public void setNik(String nik) {
        this.nik = nik;
    }

    /**
     * Gets the religion of the person.
     *
     * @return The religion.
     */
    public String getReligion() {
        return religion;
    }

    /**
     * Sets the religion of the person.
     *
     * @param religion The religion to set.
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * Gets the gender or sex of the person.
     *
     * @return The gender or sex.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Sets the gender or sex of the person.
     *
     * @param sex The gender or sex to set.
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Gets the citizenship status of the person.
     *
     * @return The citizenship status.
     */
    public String getCitizenship() {
        return citizenship;
    }

    /**
     * Sets the citizenship status of the person.
     *
     * @param citizenship The citizenship status to set.
     */
    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    /**
     * Gets information about the birth of the person.
     *
     * @return The birth information.
     */
    public String getBirthInfo() {
        return birthInfo;
    }

    /**
     * Sets information about the birth of the person.
     *
     * @param birthInfo The birth information to set.
     */
    public void setBirthInfo(String birthInfo) {
        this.birthInfo = birthInfo;
    }

    /**
     * Gets the person job.
     *
     * @return the person job.
     */
    public String getJob() {
        return job;
    }

    /**
     * Sets the person job.
     *
     * @param job the person job.
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * Gets the address of the person.
     *
     * @return The address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the person.
     *
     * @param address The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * The constructor of KtpDTO. It takes in parameters representing personal information.
     *
     * @param name        The name of a person.
     * @param nik         The National Identification Number (NIK) of a person.
     * @param religion    The religion of the person.
     * @param sex         The gender or sex of a person.
     * @param citizenship The citizenship status of the person.
     * @param birthInfo   The birth information of the person in the specified date format.
     * @param address     The address of the person.
     */
    public KTPModel(
            String name,
            String nik,
            String religion,
            String sex,
            String citizenship,
            String birthInfo,
            String address) {
        this.name = name;
        this.nik = nik;
        this.religion = religion;
        this.sex = sex;
        this.citizenship = citizenship;
        this.birthInfo = birthInfo;
        this.address = address;
    }
}
