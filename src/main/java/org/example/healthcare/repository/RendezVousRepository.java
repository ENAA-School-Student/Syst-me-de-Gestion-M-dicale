package org.example.healthcare.repository;

import lombok.Data;
import org.example.healthcare.entity.MedecinEntity;
import org.example.healthcare.entity.PatientEntity;
import org.example.healthcare.entity.RendezVousEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVousEntity ,Long> {


    List<RendezVousEntity> findByPatientId(Long patientId);

    List<RendezVousEntity> findByMedecinId(Long medecinId);
}