package org.example.healthcare.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.PatientDto;
import org.example.healthcare.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

   private final PatientService patientService;
    @PostMapping
    public PatientDto ajouterPatient(@RequestBody PatientDto dto){
        return patientService.ajouterPatient(dto);
    }

    @PutMapping("/{id}")
    public PatientDto modifierPatient(@PathVariable Long id,@RequestBody PatientDto dto){
        return patientService.modifierPatient(id,dto);
    }
    @DeleteMapping("/{id}")
    public void supprimerPatient(@PathVariable Long id){
        patientService.SupprimerPatient(id);
    }

    @GetMapping
    public List<PatientDto> ListerPatients(){
        return patientService.ListerPatients();
    }
    @GetMapping("/{id}")
    public PatientDto consulterPatient(@PathVariable Long id){
        return patientService.ConsulterPatient(id);
    }

}
