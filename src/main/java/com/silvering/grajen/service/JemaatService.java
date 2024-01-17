package com.silvering.grajen.service;

import com.silvering.grajen.dto.JemaatDTO;
import com.silvering.grajen.model.FileModel;
import com.silvering.grajen.model.JemaatModel;
import com.silvering.grajen.repository.FileRepository;
import com.silvering.grajen.repository.JemaatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JemaatService {
    private final JemaatRepository jemaatRepository;
    private final FileRepository fileRepository;

    @Autowired
    public JemaatService(JemaatRepository jemaatRepository, FileRepository fileRepository) {
        this.jemaatRepository = jemaatRepository;
        this.fileRepository = fileRepository;
    }

    public JemaatModel createJemaat(JemaatDTO jemaatDTO) {
        FileModel file = new FileModel();

        file.setUploadedAt(new Date());
        file.setPath("vincent");

        file = fileRepository.save(file);

        JemaatModel jemaat = new JemaatModel();
        jemaat.setMemberNumber(jemaatDTO.getMemberNumber());
        jemaat.setName(jemaatDTO.getName());
        jemaat.setNik(jemaatDTO.getNik());
        jemaat.setBirthplace(jemaatDTO.getBirthplace());
        jemaat.setBirthdate(jemaatDTO.getBirthdate());
        jemaat.setReligion(jemaatDTO.getReligion());
        jemaat.setKtp(file);
        jemaat.setKk(file);

        jemaat = jemaatRepository.save(jemaat);

        return jemaat;
    }
}
