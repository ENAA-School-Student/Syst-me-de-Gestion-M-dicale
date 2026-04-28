package org.example.healthcare.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.DossierRequest;
import org.example.healthcare.dto.DossierResponse;
import org.example.healthcare.service.DossierMedicalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dossier")
@Tag(name = "Gestion de DossierMedical")
public class DossierMedicalController {

    private  final DossierMedicalService dossierMedicalService;

    @PostMapping
    @Operation(summary = "Ajouter un Dossier")
    public ResponseEntity<DossierResponse> creerDossier(@RequestBody DossierRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(dossierMedicalService.CreerDossier(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter dossier médical")
    public ResponseEntity<DossierResponse> consulterDossier(@PathVariable Long id){
        return ResponseEntity.ok(dossierMedicalService.consulterDossier(id));
    }

    @PutMapping("/{id}/diagnostic")
    @Operation(summary = "Ajouter diagnostic")
    public ResponseEntity<DossierResponse> ajouterDiagnostic(@PathVariable Long id,@RequestParam String diagnostic){
        return ResponseEntity.ok(dossierMedicalService.ajouterDiagnostic(id,diagnostic));
    }

    @PutMapping("/{id}/observations")
    @Operation(summary = "ajouter observations")
    public ResponseEntity<DossierResponse> ajouterObservation(@PathVariable Long id,@RequestParam String observations){
        return ResponseEntity.ok(dossierMedicalService.ajouterObservations(id,observations));
    }
}
