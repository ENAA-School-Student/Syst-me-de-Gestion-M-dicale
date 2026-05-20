package org.example.healthcare.repository;

import org.example.healthcare.entity.PatientEntity;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    Page<PatientEntity> findAll(Pageable pageable);


}
