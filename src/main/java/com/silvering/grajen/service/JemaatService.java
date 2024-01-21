package com.silvering.grajen.service;

import com.silvering.grajen.dto.JemaatDTO;
import com.silvering.grajen.model.FileModel;
import com.silvering.grajen.model.JemaatModel;
import com.silvering.grajen.repository.JemaatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JemaatService {
    private final JemaatRepository jemaatRepository;
    private final FileService fileService;

    @Autowired
    public JemaatService(JemaatRepository jemaatRepository, FileService fileService) {
        this.jemaatRepository = jemaatRepository;
        this.fileService = fileService;
    }

    @Transactional
    public JemaatModel createJemaat(JemaatDTO jemaatDTO) {
        FileModel ktp = fileService.createFile("vincent");
        FileModel kk = fileService.createFile("vincent");

        JemaatModel jemaat = buildJemaat(jemaatDTO, ktp, kk);

        return jemaatRepository.save(jemaat);
    }

    @Transactional
    public void deleteJemaat(Long id) {
        if (jemaatRepository.existsById(id)) {
            JemaatModel jemaat = jemaatRepository.findById(id).orElseThrow();

            fileService.deleteFile(jemaat.getKtp().getId());
            fileService.deleteFile(jemaat.getKk().getId());

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

    // Need to delete the old file too
    public JemaatModel updateFile(Long id, String path) {
        JemaatModel _jemaat = jemaatRepository.findById(id).orElseThrow();

        _jemaat.getKtp().setPath(path);
        _jemaat.getKk().setPath(path);

        return jemaatRepository.save(_jemaat);
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
