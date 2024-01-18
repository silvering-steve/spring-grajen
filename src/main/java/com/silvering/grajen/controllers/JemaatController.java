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
        try {
            List<JemaatModel> jemaat = new ArrayList<>(jemaatRepository.findAll());

            return new ResponseEntity<>(jemaat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/jemaat/{id}")
    public ResponseEntity<JemaatModel> getJemaatById(@PathVariable Long id) {
        try {
            JemaatModel jemaat = jemaatRepository.findById(id).orElseThrow(() -> new RuntimeException("Jemaat not found"));

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

    @PutMapping("/jemaat/{id}")
    public ResponseEntity<JemaatModel> updateJemaat(@PathVariable Long id, @RequestBody @Valid JemaatDTO jemaat) {
        try {
            JemaatModel jemaatModel = jemaatService.updateJemaat(id, jemaat);

            return new ResponseEntity<>(jemaatModel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/jemaat/{id}")
    public ResponseEntity<HttpStatus> deleteJemaat(@PathVariable Long id) {
        try {
            jemaatService.deleteJemaat(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
