//package org.example.healthcare.service;
//
//import org.example.healthcare.dto.*;
//import org.example.healthcare.enums.StatutRendezVous;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//@Transactional
//class RendezVousServiceTest {
//    @Autowired
//    private  MedecinService medecinService;
//    @Autowired
//    private  PatientService patientService;
//    @Autowired
//    private RendezVousService rendezVousService;
//
//    @Test
//    void chercherPatient() {
//
//        PatientRequestDto patient = new PatientRequestDto();
//        patient.setUsername("ali");
//        patient.setPrenom("basri");
//        patient.setEmail("ali@gmail.com");
//        patient.setTelephone("07665544");
//        patient.setPassword("123456");
//        patient.setDateNaissance(LocalDate.of(2026,8,12));
//        MedecinRequestDto medecin = new MedecinRequestDto();
//        medecin.setUsername("soufoane");
//        medecin.setSpecialite("cardio");
//        medecin.setTelephone("09887766");
//        medecin.setEmail("dr@gmail.com");
//        medecin.setPassword("123456");
//
//        PatientRequestDto savedPatient = patientService.ajouterPatient(patient);
//        MedecinRequestDto savedMedecin = medecinService.ajouterMedecin(medecin);
//
//        RendezVousDto rdv = new RendezVousDto(
//                LocalDate.now(),
//                StatutRendezVous.CONFIRME,
//                savedPatient.getId(),
//                savedMedecin.getId());
//
//        rendezVousService.creerRendezVous(rdv);
//        List<RendezVousPatientResponse> result = rendezVousService.chercherPatient(savedPatient.getId());
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//    }
//
//    @Test
//    void chercherMedecin() {
//
//        PatientRequestDto patient = new PatientRequestDto();
//        patient.setUsername("ali");
//        patient.setPrenom("basri");
//        patient.setEmail("ali@gmail.com");
//        patient.setTelephone("07665544");
//        patient.setPassword("123456");
//        patient.setDateNaissance(LocalDate.of(2026,8,12));
//        MedecinRequestDto medecin = new MedecinRequestDto();
//        medecin.setUsername("soufoane");
//        medecin.setSpecialite("cardio");
//        medecin.setTelephone("09887766");
//        medecin.setEmail("dr@gmail.com");
//        medecin.setPassword("123456");
//        PatientRequestDto savedPatient = patientService.ajouterPatient(patient);
//        MedecinRequestDto savedMedecin = medecinService.ajouterMedecin(medecin);
//
//        RendezVousDto rdv = new RendezVousDto(
//                LocalDate.now(),
//                StatutRendezVous.PLANIFIE,
//                savedPatient.getId(),
//                savedMedecin.getId());
//
//        rendezVousService.creerRendezVous(rdv);
//
//        List<RendezVousMedecinResponse> result = rendezVousService.chercherMedecin(savedMedecin.getId());
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//    }
//}