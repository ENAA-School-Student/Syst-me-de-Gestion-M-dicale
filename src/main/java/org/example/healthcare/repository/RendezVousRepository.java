package org.example.healthcare.repository;

import org.example.healthcare.entity.RendezVousEntity;
import org.example.healthcare.enums.StatutRendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVousEntity ,Long> {

    Page<RendezVousEntity> findAll(Pageable pageable);
    List<RendezVousEntity> findByPatientId(Long patientId);

    List<RendezVousEntity> findByMedecinId(Long medecinId);
    Page<RendezVousEntity> findByStatut(StatutRendezVous statut, Pageable pageable);
}
