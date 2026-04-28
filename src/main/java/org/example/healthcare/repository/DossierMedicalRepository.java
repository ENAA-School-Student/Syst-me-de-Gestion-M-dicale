package org.example.healthcare.repository;

import org.example.healthcare.entity.DossierMedicalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierMedicalRepository extends JpaRepository<DossierMedicalEntity ,Long> {
}
