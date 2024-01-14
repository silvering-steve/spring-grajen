package com.silvering.grajen.controllers;

import com.silvering.grajen.model.UmatModel;
import com.silvering.grajen.repository.UmatRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/umat")
public class UmatController {
    private final UmatRepository umatRepository;

    @Autowired
    public UmatController(UmatRepository umatRepository) {
        this.umatRepository = umatRepository;
    }

    @GetMapping("")
    public ResponseEntity<List<UmatModel>> getAllUmat(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "nik", required = false) String nik,
            @RequestParam(value = "religion", required = false) String religion
    ) {
        try {
            List<UmatModel> umatList;

            if (name != null) {
                umatList = umatRepository.findByNameContaining(name);
            } else if (nik != null) {
                umatList = umatRepository.findByNikContaining(nik);
            } else if (religion != null) {
                umatList = umatRepository.findByReligion(religion);
            } else {
                umatList = umatRepository.findAll();
            }

            return new ResponseEntity<>(umatList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{number}")
    public ResponseEntity<UmatModel> getUmatByIdOrMemberNumber(@PathVariable String number) {
        try {
            if (number.matches("[0-9]+$")) {
                return umatRepository.findById(Long.valueOf(number))
                        .map(umatData -> new ResponseEntity<>(umatData, HttpStatus.OK))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
            }
            else {
                return umatRepository.findByMemberNumber(number)
                        .map(umatData -> new ResponseEntity<>(umatData, HttpStatus.OK))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<UmatModel> createUmat(@Valid @RequestBody UmatModel umatModel) {
        try {
            UmatModel _umat = umatRepository.save(new UmatModel(
                    umatModel.getMemberNumber(),
                    umatModel.getNik(),
                    umatModel.getName(),
                    umatModel.getBirthdate(),
                    umatModel.getBirthplace(),
                    umatModel.getReligion()
                    ));

            return new ResponseEntity<>(_umat, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUmat(@PathVariable Long id) {
        try {
            umatRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
