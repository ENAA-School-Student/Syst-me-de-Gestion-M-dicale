package org.example.healthcare.service;

import org.example.healthcare.dto.PatientDto;
import org.example.healthcare.dto.PatientRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Test
    @DisplayName("Test d'ajouter patient")
    void ajouterPatient() {
        PatientRequestDto patient=new PatientRequestDto();
        patient.setUsername("soufiane");
        patient.setPrenom("basri");
        patient.setEmail("soufiane@gmail.com");
        patient.setTelephone("07665544");
        patient.setDateNaissance(LocalDate.of(2026,8,12));

        PatientRequestDto result=patientService.ajouterPatient(patient);

        assertEquals("soufiane",result.getUsername());

    }
}