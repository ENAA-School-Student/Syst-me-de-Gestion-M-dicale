package org.example.healthcare.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.RendezVousDto;
import org.example.healthcare.dto.RendezVousMedecinResponse;
import org.example.healthcare.dto.RendezVousPatientResponse;
import org.example.healthcare.service.RendezVousService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rendezVous")
@Tag(name = "Gestion des Rendez-vous")
public class RendezVousController {

    private  final RendezVousService rendezVousService;

    @PostMapping
    @Operation(summary ="creer rendez vous")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RendezVousDto> creerRendezVous(@Valid @RequestBody RendezVousDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(rendezVousService.creerRendezVous(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "modifier Rendez vous")
    public ResponseEntity<RendezVousDto> modifierRendezVous( @PathVariable Long id,@Valid @RequestBody RendezVousDto dto){
        return ResponseEntity.ok(rendezVousService.modifierRendezVous(id,dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN','PATIENT')")
    @GetMapping
    @Operation(summary = "Lister rendez-vous")
    public ResponseEntity<List<RendezVousDto>> listerRendezVous(){
        return ResponseEntity.ok(rendezVousService.listerRendezVous());
    }
    @PatchMapping("/{id}/annuler")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Annuler rendez-vous")
    public ResponseEntity<RendezVousDto> annullerRendezVous( @PathVariable Long id){
        return ResponseEntity.ok(rendezVousService.AnnuleRendezVous(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Rechercher par patient")
    public ResponseEntity<List<RendezVousPatientResponse>> chercherPatient( @PathVariable Long patientId){
        return ResponseEntity.ok(rendezVousService.chercherPatient(patientId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/medecin/{medecinId}")
    @Operation(summary = "Rechercher par medecin")
    public ResponseEntity<List<RendezVousMedecinResponse>> chercherMedecin( @PathVariable Long medecinId){
        return ResponseEntity.ok(rendezVousService.chercherMedecin(medecinId));
    }
}


