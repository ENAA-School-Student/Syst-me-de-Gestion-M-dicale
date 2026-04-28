package org.example.healthcare.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.RendezVousDto;
import org.example.healthcare.dto.RendezVousMedecinResponse;
import org.example.healthcare.dto.RendezVousPatientResponse;
import org.example.healthcare.dto.RendezVousResponse;
import org.example.healthcare.service.RendezVousService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rendezVous")
@Tag(name = "Gestion des Rendez-vous")
public class RendezVousController {

    private  final RendezVousService rendezVousService;

    @PostMapping
    @Operation(summary ="creer rendez vous")
    public ResponseEntity<RendezVousResponse> creerRendezVous(@Valid @RequestBody RendezVousDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(rendezVousService.creerRendezVous(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "modifier Rendez vous")
    public ResponseEntity<RendezVousResponse> modifierRendezVous( @PathVariable Long id,@Valid @RequestBody RendezVousDto dto){
        return ResponseEntity.ok(rendezVousService.modifierRendezVous(id,dto));
    }
    @GetMapping
    @Operation(summary = "Lister rendez-vous")
    public ResponseEntity<List<RendezVousResponse>> listerRendezVous(){
        return ResponseEntity.ok(rendezVousService.listerRendezVous());
    }
    @PatchMapping("/{id}/annuler")
    @Operation(summary = "Annuler rendez-vous")
    public ResponseEntity<RendezVousResponse> annullerRendezVous( @PathVariable Long id){
        return ResponseEntity.ok(rendezVousService.AnnuleRendezVous(id));
    }
    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Rechercher par patient")
    public ResponseEntity<List<RendezVousPatientResponse>> chercherPatient( @PathVariable Long patientId){
        return ResponseEntity.ok(rendezVousService.chercherPatient(patientId));
    }
    @GetMapping("/medecin/{medecinId}")
    @Operation(summary = "Rechercher par medecin")
    public ResponseEntity<List<RendezVousMedecinResponse>> chercherMedecin( @PathVariable Long medecinId){
        return ResponseEntity.ok(rendezVousService.chercherMedecin(medecinId));
    }
}


