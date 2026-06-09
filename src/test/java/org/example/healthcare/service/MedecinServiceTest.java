package org.example.healthcare.service;
import org.example.healthcare.dto.MedecinDto;
import org.example.healthcare.dto.MedecinRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class MedecinServiceTest {
    @Autowired
    private MedecinService medecinService;
    @Test
    @DisplayName("Ajouter un médecin avec succès")
    void ajouterMedecin() {
        MedecinRequestDto dto = new MedecinRequestDto();
        dto.setUsername("medecin1");
        dto.setEmail("medecin1@gmail.com");
        dto.setTelephone("0612345678");
        dto.setSpecialite("Cardiologue");
        dto.setPassword("password123");

        MedecinRequestDto result = medecinService.ajouterMedecin(dto);
        assertNotNull(result);
        assertEquals("medecin1", result.getUsername());
        assertEquals("medecin1@gmail.com", result.getEmail());
        assertEquals("0612345678", result.getTelephone());
        assertEquals("Cardiologue", result.getSpecialite());
    }

    @Test
    @DisplayName("Modifier un médecin")
    void modifierMedecin() {
        MedecinRequestDto createDto = new MedecinRequestDto();
        createDto.setUsername("medecin1");
        createDto.setEmail("medecin1@gmail.com");
        createDto.setTelephone("0612345678");
        createDto.setSpecialite("Cardiologue");
        createDto.setPassword("password123");

        MedecinRequestDto saved = medecinService.ajouterMedecin(createDto);

        MedecinDto updateDto = new MedecinDto();
        updateDto.setEmail("newemail@gmail.com");
        updateDto.setTelephone("0700000000");
        updateDto.setSpecialite("Dermatologue");
        MedecinDto updated = medecinService.modifierMedecin(saved.getId(), updateDto);
        assertNotNull(updated);
        assertEquals("newemail@gmail.com", updated.getEmail());
        assertEquals("0700000000", updated.getTelephone());
        assertEquals("Dermatologue", updated.getSpecialite());
    }

    @Test
    @DisplayName("Supprimer un médecin")
    void supprimerMedecin() {

        MedecinRequestDto dto = new MedecinRequestDto();
        dto.setUsername("medecin1");
        dto.setEmail("medecin1@gmail.com");
        dto.setTelephone("0612345678");
        dto.setSpecialite("Cardiologue");
        dto.setPassword("password123");

        MedecinRequestDto saved = medecinService.ajouterMedecin(dto);
        assertDoesNotThrow(() -> medecinService.supprimerMedecin(saved.getId()));
    }
}