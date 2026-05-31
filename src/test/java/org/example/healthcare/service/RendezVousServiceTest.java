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
//        PatientDto patient = new PatientDto();
//        patient.setNom("ali");
//        patient.setPrenom("basri");
//        patient.setEmail("ali@gmail.com");
//        patient.setTelephone("07665544");
//        patient.setDateNaissance(LocalDate.of(2026,8,12));
//        MedecinDto medecin = new MedecinDto();
//        medecin.setNom("soufoane");
//        medecin.setSpecialite("cardio");
//        medecin.setTelephone("09887766");
//        medecin.setEmail("dr@gmail.com");
//
//        PatientDto savedPatient = patientService.ajouterPatient(patient);
//        MedecinDto savedMedecin = medecinService.ajouterMedecin(medecin);
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
//        PatientDto patient = new PatientDto();
//        patient.setNom("ali");
//        patient.setPrenom("basri");
//        patient.setEmail("ali@gmail.com");
//        patient.setTelephone("07665544");
//        patient.setDateNaissance(LocalDate.of(2026,8,12));
//        MedecinDto medecin = new MedecinDto();
//        medecin.setNom("soufoane");
//        medecin.setSpecialite("cardio");
//        medecin.setTelephone("09887766");
//        medecin.setEmail("dr@gmail.com");
//        PatientDto savedPatient = patientService.ajouterPatient(patient);
//        MedecinDto savedMedecin = medecinService.ajouterMedecin(medecin);
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