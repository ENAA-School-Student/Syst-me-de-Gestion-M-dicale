package org.example.healthcare.service;

import org.example.healthcare.dto.PatientRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Test
    @DisplayName("Test d'ajouter patient avec succès et chiffrement du mot de passe")
    void ajouterPatient() {
        // 1. Given : إعداد البيانات المدخلة (Input)
        PatientRequestDto inputDto = new PatientRequestDto();
        inputDto.setUsername("soufiane");
        inputDto.setPrenom("basri");
        inputDto.setEmail("soufiane@gmail.com");
        inputDto.setTelephone("07665544");
        inputDto.setDateNaissance(LocalDate.of(2000, 8, 12));
        inputDto.setPassword("password123");

        PatientRequestDto resultDto = patientService.ajouterPatient(inputDto);

        assertNotNull(resultDto, "Le DTO retourné ne doit pas être null");
        assertEquals("soufiane", resultDto.getUsername(), "Le username ne correspond pas");
        assertEquals("basri", resultDto.getPrenom(), "Le prénom ne correspond pas");
        assertEquals("soufiane@gmail.com", resultDto.getEmail(), "L'email ne correspond pas");
        assertEquals("07665544", resultDto.getTelephone(), "Le téléphone ne correspond pas");
        assertEquals(LocalDate.of(2000, 8, 12), resultDto.getDateNaissance(), "La date de naissance ne correspond pas");


    }
}