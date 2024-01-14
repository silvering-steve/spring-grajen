package com.silvering.grajen.controllers;


import com.silvering.grajen.model.KTPModel;
import com.silvering.grajen.repository.KTPRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The controller for KTP API. This API is only for testing purpose and will not be used in the apps.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/ktp")
public class KTPController {
    private final KTPRepository ktpRepository;

    /**
     * Set the KTPRepository bean.
     *
     * @param ktpRepository The KTPRepository bean.
     */
    @Autowired
    public KTPController(KTPRepository ktpRepository) {
        this.ktpRepository = ktpRepository;
    }

    /**
     * Get KTP by ID.
     *
     * @param id The KTP id.
     * @return The ktp data that consist of its path and id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<KTPModel> getKTPById(@PathVariable Long id) {
        Optional<KTPModel> ktpData = ktpRepository.findById(id);

        return ktpData.map(ktpModel -> new ResponseEntity<>(ktpModel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Create a new KTP data
     *
     * @param ktpModel The data that needed to create KTP data
     * @return The KTP data that consist of its path and id.
     */
    @PostMapping("")
    public ResponseEntity<KTPModel> createKTP(@Valid @RequestBody KTPModel ktpModel) {
        try {
            KTPModel _ktp = ktpRepository.save(new KTPModel(ktpModel.getPath()));

            return new ResponseEntity<>(_ktp, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update KTP data by its id.
     *
     * @param id        The id of KTP data that will be changed
     * @param ktpModel  The new data that will replace it
     * @return The KTP data that consist of its path and id.
     */
    @PutMapping("/{id}")
    public ResponseEntity<KTPModel> updateKTP(@PathVariable Long id, @RequestBody KTPModel ktpModel) {
        Optional<KTPModel> ktpData = ktpRepository.findById(id);

        if (ktpData.isPresent()) {
            KTPModel _ktp = ktpData.get();

            _ktp.setPath(ktpModel.getPath());

            return new ResponseEntity<>(ktpRepository.save(_ktp), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete KTP data by its id.
     *
     * @param id The KTP data id.
     * @return The current status of the data.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteKTP(@PathVariable Long id) {
        try {
            ktpRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
