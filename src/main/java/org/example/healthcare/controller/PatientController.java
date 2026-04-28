package org.example.healthcare.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.PatientDto;
import org.example.healthcare.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.util.List;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
@Tag(name = "Gestion des Patients")
public class PatientController {

   private final PatientService patientService;
    @PostMapping
    @Operation(summary = "Ajouter patient")
    public ResponseEntity<PatientDto> ajouterPatient(@Valid @RequestBody PatientDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.ajouterPatient(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier ptient ")
    public ResponseEntity<PatientDto> modifierPatient( @PathVariable Long id,@Valid @RequestBody PatientDto dto){
        return ResponseEntity.ok(patientService.modifierPatient(id,dto));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "supprimer ptient ")
    public ResponseEntity<Void> supprimerPatient(@PathVariable Long id){
        patientService.SupprimerPatient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Lister Patients ")
    public ResponseEntity<List<PatientDto>> ListerPatients(){
        return ResponseEntity.ok(patientService.ListerPatients());
    }
    @GetMapping("/{id}")
    @Operation(summary = "Consulter patient")
    public ResponseEntity<PatientDto> consulterPatient( @PathVariable Long id){
        return ResponseEntity.ok(patientService.ConsulterPatient(id));
    }

}
