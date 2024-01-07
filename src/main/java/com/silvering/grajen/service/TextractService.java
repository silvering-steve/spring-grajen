package com.silvering.grajen.service;

import com.silvering.grajen.model.KTPModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.textract.TextractClient;
import software.amazon.awssdk.services.textract.model.*;

import java.io.IOException;

/**
 * Service class for interacting the output from AWS Textract.
 */
@Service
public class TextractService {
    private final TextractClient textractClient;

    private final DocumentService documentService;

    @Value("${aws.bucketName}")
    private String bucketName;

    /**
     * Constructor for the TextractService class.
     *
     * @param textractClient  The TextractClient for interacting with Amazon Textract
     * @param documentService The DocumentService for processing the output from Amazon Textract
     */
    @Autowired
    public TextractService(TextractClient textractClient, DocumentService documentService) {
        this.textractClient = textractClient;
        this.documentService = documentService;
    }

    /**
     * Extracts text from an S3 object.
     *
     * @param path The path to the S3 object.
     * @return A KTPModel object containing the extracted data.
     * @throws IllegalStateException If the S3 object cannot be processed.
     * @throws IOException           If the S3 object cannot be read.
     */
    public KTPModel extractDataS3(String path) throws IOException {
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

    public String extractData() {
        return null;
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
