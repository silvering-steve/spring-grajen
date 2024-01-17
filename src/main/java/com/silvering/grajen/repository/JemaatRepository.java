package com.silvering.grajen.repository;

import com.silvering.grajen.model.JemaatModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface JemaatRepository extends JpaRepository<JemaatModel, Long> {
    List<JemaatModel> findByMemberNumberContaining(String memberNumber);

    List<JemaatModel> findByNikContaining(String nik);

    List<JemaatModel> findByNameContaining(String name);

    List<JemaatModel> findByReligionContaining(String religion);
}
