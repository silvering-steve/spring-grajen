package com.silvering.grajen.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.silvering.grajen.model.KTPModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.textract.model.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Service class for processing the output from AWS Textract
 */
@Service
public class DocumentService {
    private final ObjectMapper objectMapper;

    /**
     * Constructor for the DocumentService class.
     *
     * @param objectMapper The ObjectMapper for converting JSON to Java objects.
     */
    @Autowired
    public DocumentService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Extracts ktp data in key-value pairs from an AnalyzeDocumentResponse
     *
     * @param documentResponse The response from document analysis.
     * @return A KTPModel object containing the extracted key-value data.
     * @throws JsonProcessingException If the key-value data cannot be converted to JSON.
     */
    public KTPModel extractKTPData(AnalyzeDocumentResponse documentResponse) throws JsonProcessingException {
        // Map to store word information (id -> text)
        Map<String, String> wordHash = buildWordHash(documentResponse);

        // Map to store value information (id -> text)
        Map<String, String> valueHash = buildValueHash(documentResponse, wordHash);

        // Map to store key-value data pairs
        Map<String, String> ktpData = new HashMap<>();

        // Process each block in the document response

        for (Block block : documentResponse.blocks()) {
            if (block.blockType() == BlockType.KEY_VALUE_SET && block.entityTypes().getFirst() == EntityType.KEY) {
                processKeyValueBlock(block, wordHash, valueHash, ktpData);
            }
        }

        // Convert map object to json
        String ktpJson = objectMapper.writeValueAsString(ktpData);

        // Return the deserialize json object as KTPModel
        return objectMapper.readValue(ktpJson, KTPModel.class);
    }

    /**
     * Builds a map of word information (id -> text) from the document response.
     *
     * @param documentResponse The response from document analysis.
     * @return A map of word information (id -> text).
     */
    private Map<String, String> buildWordHash(AnalyzeDocumentResponse documentResponse) {
        Map<String, String> wordHash = new HashMap<>();

        // Extract word information and populate the map
        documentResponse.blocks().stream()
                .filter(block -> block.blockType() == BlockType.WORD)
                .forEach(block -> wordHash.put(block.id(), Objects.requireNonNull(block.text())));

        return wordHash;
    }

    /**
     * Builds a map of value information (id -> text) from the document response.
     *
     * @param documentResponse  The response from document analysis.
     * @param wordHash          The word hash containing word information.
     * @return A map of value information (id -> text).
     */
    private Map<String, String> buildValueHash(AnalyzeDocumentResponse documentResponse, Map<String, String> wordHash) {
        Map<String, String> valueHash = new HashMap<>();

        // Extract value information and populate the map
        documentResponse.blocks().stream()
                .filter(block -> block.blockType() == BlockType.KEY_VALUE_SET)
                .filter(block -> block.entityTypes().getFirst() == EntityType.VALUE)
                .forEach(block -> valueHash.put(block.id(),
                        Objects.requireNonNull(block.relationships().stream()
                                .filter(relationship -> relationship.type() == RelationshipType.CHILD)
                                .flatMap(relationship -> relationship.ids().stream())
                                .map(wordHash::get)
                                .collect(Collectors.joining(" ")))));

        return valueHash;
    }

    /**
     * Processes a key-value block and extracts key-value data from it.
     *
     * @param block         The key-value block to process.
     * @param wordHash      The word hash containing word information.
     * @param valueHash     The value hash containing value information.
     * @param keyValueHash  The key-value hash to store key-value data.
     */
    private void processKeyValueBlock(
            Block block,
            Map<String, String> wordHash,
            Map<String, String> valueHash,
            Map<String, String> keyValueHash
    ) {
        // Extract key name from child relationships
        String keyName = block.relationships().stream()
                .filter(relationship -> relationship.type() == RelationshipType.CHILD)
                .flatMap(relationship -> relationship.ids().stream())
                .map(wordHash::get)
                .collect(Collectors.joining())
                .replaceAll("[\\p{Punct}\\s]", "");

        // Extract the key value if the block has it
        String keyValue = block.relationships().stream()
                .filter(relationship -> relationship.type() == RelationshipType.VALUE)
                .flatMap(relationship -> relationship.ids().stream())
                .map(valueHash::get)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" "));

        // Add to keyValueHash if both keyName and keyValue are not empty
        if (!keyName.isEmpty() && !keyValue.isEmpty()) {
            keyValueHash.put(keyName, keyValue);
        }
    }
}
