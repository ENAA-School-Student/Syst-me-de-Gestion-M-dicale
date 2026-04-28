package org.example.healthcare.service;

import org.example.healthcare.dto.PatientDto;
import org.example.healthcare.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Test
    void ajouterPatient() {
        PatientDto patient=new PatientDto();
        patient.setNom("soufiane");
        patient.setPrenom("basri");
        patient.setTelephone("07665455");
        patient.setEmail("soufiane@gmail.com");
        patient.setDateNaissance(LocalDate.of(2026,8,12));

        PatientDto result=patientService.ajouterPatient(patient);

        assertEquals("soufiane",result.getNom());

    }
}