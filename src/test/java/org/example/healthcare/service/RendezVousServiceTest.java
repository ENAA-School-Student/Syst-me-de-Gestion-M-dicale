package org.example.healthcare.service;

import org.example.healthcare.dto.RendezVousMedecinResponse;
import org.example.healthcare.dto.RendezVousPatientResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class RendezVousServiceTest {

    @Autowired
    private RendezVousService rendezVousService;

    @Test
    @DisplayName("Rechercher les rendez-vous d'un patient")
    void chercherPatient() {

        Long patientId = 1L;
        List<RendezVousPatientResponse> result = rendezVousService.chercherPatient(patientId);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Rechercher les rendez-vous d'un médecin")
    void chercherMedecin() {

        Long medecinId = 1L;
        List<RendezVousMedecinResponse> result = rendezVousService.chercherMedecin(medecinId);
        assertNotNull(result);
    }
}