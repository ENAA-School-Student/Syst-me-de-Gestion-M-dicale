package org.example.healthcare.service;
import org.example.healthcare.dto.MedecinRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MedecinServiceTest {

    @Autowired
    private MedecinService medecinService;

    @Test
    void supprimerMedecin() {
        MedecinRequestDto medecin = new MedecinRequestDto();
        medecin.setUsername("ali");
        medecin.setEmail("ali@gmail.com");
        medecin.setSpecialite("dt");
        medecin.setTelephone("0777665544");
        medecin.setPassword("123456");

        MedecinRequestDto save = medecinService.ajouterMedecin(medecin);

        Long medecinId = medecin.getId();

        medecinService.supprimerMedecin(medecinId);
        var list = medecinService.listerMedecins(0, 10, "ASC");
        boolean exists = list.getContent().stream().anyMatch(m -> m.getUsername().equals("ali"));
        assertFalse(exists);
    }

    @Test
    void ListermEdecins() {
        MedecinRequestDto medecin1 = new MedecinRequestDto();
        medecin1.setUsername("ali");
        medecin1.setEmail("ali@gmail.com");
        medecin1.setSpecialite("dt");
        medecin1.setTelephone("0777665544");
        medecin1.setPassword("123456");


        MedecinRequestDto medecin2 = new MedecinRequestDto();
        medecin2.setUsername("aya");
        medecin2.setEmail("aya@gmail.com");
        medecin2.setSpecialite("dt");
        medecin2.setTelephone("0877665544");
        medecin2.setPassword("123456");

        medecinService.ajouterMedecin(medecin1);
        medecinService.ajouterMedecin(medecin2);

        var rsult = medecinService.listerMedecins(0, 10, "ASC");
        assertNotNull(rsult);
        assertFalse(rsult.isEmpty());
    }
}