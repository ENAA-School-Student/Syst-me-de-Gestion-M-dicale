package org.example.healthcare.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.PatientDto;
import org.example.healthcare.dto.PatientRequestDto;
import org.example.healthcare.service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
@Tag(name = "Gestion des Patients")
public class PatientController {

   private final PatientService patientService;
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Ajouter patient")
    public ResponseEntity<PatientRequestDto> ajouterPatient(@Valid @RequestBody PatientRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.ajouterPatient(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    @Operation(summary = "Modifier ptient ")
    public ResponseEntity<PatientDto> modifierPatient( @PathVariable Long id,@Valid @RequestBody PatientDto dto){
        return ResponseEntity.ok(patientService.modifierPatient(id,dto));
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "supprimer ptient ")
    public ResponseEntity<Void> supprimerPatient(@PathVariable Long id){
        patientService.SupprimerPatient(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Lister Patients ")
    public ResponseEntity<Page<PatientDto>> ListerPatients(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "5") int size,
            @RequestParam(defaultValue = "prenom")String sortBy,
            @RequestParam(defaultValue = "asc")String sortDeriction)
    {
        return ResponseEntity.ok(patientService.ListerPatients(page,size,sortBy,sortDeriction));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/cache-test")
    public ResponseEntity<List<PatientDto>> testCache() {
        return ResponseEntity.ok(patientService.testCache());
    }

    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    @GetMapping("/{id}")
    @Operation(summary = "Consulter patient")
    public ResponseEntity<PatientDto> consulterPatient( @PathVariable Long id){
        return ResponseEntity.ok(patientService.ConsulterPatient(id));
    }
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    @GetMapping("/search")
    public ResponseEntity<Page<PatientDto>> rechercherPatient(
            @RequestParam String nom,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(patientService.rechercherPatientParNom(nom, page, size));
    }
}
