package org.example.healthcare.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.DossierDto;
import org.example.healthcare.service.DossierMedicalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dossier")
@Tag(name = "Gestion de DossierMedical")
public class DossierMedicalController {

    private  final DossierMedicalService dossierMedicalService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Ajouter un Dossier")
    public ResponseEntity<DossierDto> creerDossier(@Valid @RequestBody DossierDto request){
        return ResponseEntity.status(HttpStatus.CREATED).body(dossierMedicalService.CreerDossier(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN','PATIENT')")
    @Operation(summary = "Consulter dossier médical")
    public ResponseEntity<DossierDto> consulterDossier( @PathVariable Long id){
        return ResponseEntity.ok(dossierMedicalService.consulterDossier(id));
    }

    @PutMapping("/{id}/diagnostic")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    @Operation(summary = "Ajouter diagnostic")
    public ResponseEntity<DossierDto> ajouterDiagnostic( @PathVariable Long id,@Valid @RequestParam String diagnostic){
        return ResponseEntity.ok(dossierMedicalService.ajouterDiagnostic(id,diagnostic));
    }

    @PutMapping("/{id}/observations")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    @Operation(summary = "ajouter observations")
    public ResponseEntity<DossierDto> ajouterObservation( @PathVariable Long id,@Valid @RequestParam String observations){
        return ResponseEntity.ok(dossierMedicalService.ajouterObservations(id,observations));
    }
}
