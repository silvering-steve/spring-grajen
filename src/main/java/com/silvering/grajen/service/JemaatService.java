package com.silvering.grajen.service;

import com.silvering.grajen.dto.JemaatDTO;
import com.silvering.grajen.model.FileModel;
import com.silvering.grajen.model.JemaatModel;
import com.silvering.grajen.repository.FileRepository;
import com.silvering.grajen.repository.JemaatRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JemaatService {
    private final JemaatRepository jemaatRepository;
    private final FileRepository fileRepository;

    @Autowired
    public JemaatService(JemaatRepository jemaatRepository, FileRepository fileRepository) {
        this.jemaatRepository = jemaatRepository;
        this.fileRepository = fileRepository;
    }

    @Transactional
    public JemaatModel createJemaat(JemaatDTO jemaatDTO) {
        FileModel ktp = createFile("vincent");
        FileModel kk = createFile("vincent");

        JemaatModel jemaat = buildJemaat(jemaatDTO, ktp, kk);

        return jemaatRepository.save(jemaat);
    }

    @Transactional
    public void deleteJemaat(Long id) {
        if (jemaatRepository.existsById(id)) {
            JemaatModel jemaat = jemaatRepository.findById(id).orElseThrow();

            fileRepository.deleteById(jemaat.getKtp().getId());
            fileRepository.deleteById(jemaat.getKk().getId());

            jemaatRepository.deleteById(id);
        }
    }

    @Transactional
    public JemaatModel updateJemaat(Long id, JemaatDTO jemaatDTO) {
        JemaatModel _jemaat = jemaatRepository.findById(id).orElseThrow();

        _jemaat.setMemberNumber(jemaatDTO.getMemberNumber());
        _jemaat.setName(jemaatDTO.getName());
        _jemaat.setNik(jemaatDTO.getNik());
        _jemaat.setBirthplace(jemaatDTO.getBirthplace());
        _jemaat.setBirthdate(jemaatDTO.getBirthdate());
        _jemaat.setReligion(jemaatDTO.getReligion());

        return jemaatRepository.save(_jemaat);
    }

    private FileModel createFile(String path) {
        FileModel file = new FileModel();
        file.setUploadedAt(LocalDateTime.now());
        file.setPath(path);

        return fileRepository.save(file);
    }

    private JemaatModel buildJemaat(JemaatDTO jemaatDTO, FileModel ktp, FileModel kk) {
        JemaatModel jemaat = new JemaatModel();
        jemaat.setMemberNumber(jemaatDTO.getMemberNumber());
        jemaat.setName(jemaatDTO.getName());
        jemaat.setNik(jemaatDTO.getNik());
        jemaat.setBirthplace(jemaatDTO.getBirthplace());
        jemaat.setBirthdate(jemaatDTO.getBirthdate());
        jemaat.setReligion(jemaatDTO.getReligion());
        jemaat.setKtp(ktp);
        jemaat.setKk(kk);

        return jemaat;
    }
}
