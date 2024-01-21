package com.silvering.grajen.service;

import com.silvering.grajen.model.FileModel;
import com.silvering.grajen.repository.FileRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

// I don't need this class, I just can use DTO or the model :/
@Service
public class FileService {
    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public FileModel createFile(String path) {
        FileModel _file = new FileModel();

        _file.setPath(path);
        _file.setUploadedAt(LocalDateTime.now());

        return fileRepository.save(_file);
    }

    // Should use versioned instead replacing
    @Transactional
    public FileModel updateFile(Long id, String path) {
        FileModel _file = fileRepository.findById(id).orElseThrow();

        _file.setPath(path);
        _file.setUploadedAt(LocalDateTime.now());

        return fileRepository.save(_file);
    }

    @Transactional
    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }
}
