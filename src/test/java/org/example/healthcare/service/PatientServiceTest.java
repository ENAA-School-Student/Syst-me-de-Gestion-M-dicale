package org.example.healthcare.service;

import jakarta.transaction.Transactional;
import org.example.healthcare.dto.PatientRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PatientServiceTest {

    @Autowired
    private PatientService patientService;
    @Test
    @DisplayName("Test d'ajouter patient avec succès")
    void ajouterPatient() {
        PatientRequestDto patientRequest = new PatientRequestDto();
        patientRequest.setUsername("soufiane");
        patientRequest.setPrenom("basri");
        patientRequest.setEmail("soufiane@gmail.com");
        patientRequest.setTelephone("07665544");
        patientRequest.setDateNaissance(LocalDate.of(2000, 8, 12));

        patientRequest.setPassword("password123");

        var result = patientService.ajouterPatient(patientRequest);
        assertNotNull(result);
        assertEquals("soufiane", result.getUsername());
    }
}