package com.silvering.grajen.model;

/**
 * The model for File data. WILL NOT BE USED.
 */
public class FileModel {

    /**
     * The file id in uuid4
     */
    private String fileId;

    /**
     * The file name
     */
    private String fileName;

    /**
     * The file path of the file in the S3.
     */
    private String filePath;

    /**
     * The constructor for the File model class
     *
     * @param fileId   the file id
     * @param fileName the file name
     * @param filePath the file path in the S3 bucket.
     */
    public FileModel(String fileId, String fileName, String filePath) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    /**
     * Getter for the file id.
     *
     * @return the file id.
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * Setter for the file id.
     *
     * @param fileId the field id that want to be set
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * Getter for the file name.
     *
     * @return the file name.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Setter for the file name.
     *
     * @param fileName the field name that want to be set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Getter for the file path.
     *
     * @return the file path.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Setter for the file path.
     *
     * @param filePath the field path that want to be set.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
