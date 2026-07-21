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

import java.util.List;

@RestController
@RequestMapping("/api/dossier")
@RequiredArgsConstructor
@Tag(name = "Gestion des Dossiers Médicaux")
public class DossierMedicalController {

    private final DossierMedicalService dossierMedicalService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Créer un dossier médical")
    public ResponseEntity<DossierDto> creerDossier(@Valid @RequestBody DossierDto request) {

        DossierDto dossier = dossierMedicalService.CreerDossier(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(dossier);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN','PATIENT')")
    @Operation(summary = "Liste des dossiers selon le rôle")
    public ResponseEntity<List<DossierDto>> getMesDossiers() {

        return ResponseEntity.ok(dossierMedicalService.getMesDossiers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN','PATIENT')")
    @Operation(summary = "Consulter un dossier médical")
    public ResponseEntity<DossierDto> consulterDossier(@PathVariable Long id) {

        return ResponseEntity.ok(dossierMedicalService.consulterDossier(id));
    }

    @PutMapping("/{id}/diagnostic")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    @Operation(summary = "Ajouter ou modifier le diagnostic")
    public ResponseEntity<DossierDto> ajouterDiagnostic(
            @PathVariable Long id,
            @RequestParam String diagnostic) {

        return ResponseEntity.ok(
                dossierMedicalService.ajouterDiagnostic(id, diagnostic)
        );
    }

    @PutMapping("/{id}/observations")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    @Operation(summary = "Ajouter ou modifier les observations")
    public ResponseEntity<DossierDto> ajouterObservation(
            @PathVariable Long id,
            @RequestParam String observations) {

        return ResponseEntity.ok(
                dossierMedicalService.ajouterObservations(id, observations)
        );
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Liste de tous les dossiers")
    public ResponseEntity<List<DossierDto>> getAllDossiers() {
        return ResponseEntity.ok(
                dossierMedicalService.getAllDossiers()
        );
    }

}