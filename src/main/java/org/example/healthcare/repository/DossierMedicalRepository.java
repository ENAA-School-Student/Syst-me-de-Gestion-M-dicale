package org.example.healthcare.repository;

import org.example.healthcare.entity.DossierMedicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DossierMedicalRepository extends JpaRepository<DossierMedicalEntity ,Long> {
    List<DossierMedicalEntity> findByPatientId(Long id);
}
