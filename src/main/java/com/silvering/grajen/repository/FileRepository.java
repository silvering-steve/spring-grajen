package com.silvering.grajen.repository;

import com.silvering.grajen.model.FileModel;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileModel, Long> {
    @Transactional
    void deleteById(long id);
}
