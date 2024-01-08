package com.silvering.grajen.service;

import com.silvering.grajen.model.FileModel;
import com.silvering.grajen.model.KTPModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.*;

import java.io.IOException;
import java.util.Optional;

/**
 * Service class for interacting the output from AWS Textract.
 */
@Service
public class TextractService {
    private final TextractClient textractClient;

    private final DocumentService documentService;
    private final S3Service s3Service;

    @Value("${aws.bucketName}")
    private String bucketName;

    /**
     * Constructor for the TextractService class.
     *
     * @param textractClient  The TextractClient for interacting with Amazon Textract
     * @param documentService The DocumentService for processing the output from Amazon Textract
     */
    @Autowired
    public TextractService(TextractClient textractClient, DocumentService documentService, S3Service s3Service) {
        this.textractClient = textractClient;
        this.documentService = documentService;
        this.s3Service = s3Service;
    }

    /**
     * Extracts text from an S3 object.
     *
     * @param path The path to the S3 object.
     * @return A KTPModel object containing the extracted data.
     * @throws IllegalStateException If the S3 object cannot be processed.
     * @throws IOException           If the S3 object cannot be read.
     */
    public KTPModel extractDataFromS3(String path) throws IOException {
        try {
            // Create an S3Object using the specified bucket name and path
            S3Object s3Object = createS3Object("ktp/" + path + ".jpeg");

            // Create a Document using the S3Object
            Document document = createDocument(s3Object);

            // Analyze the document using Amazon Textract
            AnalyzeDocumentResponse documentResponse = textractClient.analyzeDocument(
                    AnalyzeDocumentRequest.builder()
                            .featureTypes(FeatureType.FORMS)
                            .document(document)
                            .build()
            );

            // Extract KTP Data using DocumentService and return it in KTPModel object
            return documentService.extractKTPData(documentResponse);
        } catch (TextractException e) {
            throw new IllegalStateException("Failed to process file", e);
        }
    }

    public KTPModel extractDataFromImage(MultipartFile file) throws IOException {
        FileModel fileModel = s3Service.uploadFile(file, Optional.of("ktp/"));

        return extractDataFromS3(fileModel.getFileName());
    }

    private S3Object createS3Object(String path) {
        return S3Object.builder()
                .bucket(bucketName)
                .name(path)
                .build();
    }

    private Document createDocument(S3Object s3Object) {
        return Document.builder()
                .s3Object(s3Object)
                .build();
    }
}
