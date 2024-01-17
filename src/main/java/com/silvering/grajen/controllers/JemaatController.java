package com.silvering.grajen.controllers;

import com.silvering.grajen.dto.JemaatDTO;
import com.silvering.grajen.model.JemaatModel;
import com.silvering.grajen.repository.JemaatRepository;
import com.silvering.grajen.service.JemaatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class JemaatController {
    private final JemaatService jemaatService;
    private final JemaatRepository jemaatRepository;

    @Autowired
    public JemaatController(JemaatService jemaatService, JemaatRepository jemaatRepository) {
        this.jemaatService = jemaatService;
        this.jemaatRepository = jemaatRepository;
    }

    @GetMapping("/jemaat")
    public ResponseEntity<List<JemaatModel>> getAllJemaat() {
        List<JemaatModel> jemaat = new ArrayList<>();

        try {
            jemaat.addAll(jemaatRepository.findAll());

            return new ResponseEntity<>(jemaat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/jemaat")
    public ResponseEntity<JemaatModel> createJemaat(@RequestBody @Valid JemaatDTO jemaat) {
        try {
            JemaatModel jemaatModel = jemaatService.createJemaat(jemaat);

            return new ResponseEntity<>(jemaatModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
