package org.example.healthcare.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.PatientDto;
import org.example.healthcare.service.PatientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

   private final PatientService patientService;
    @PostMapping
    public PatientDto ajouterPatient(@RequestBody PatientDto dto){
        return patientService.ajouterPatient(dto);
    }

}
