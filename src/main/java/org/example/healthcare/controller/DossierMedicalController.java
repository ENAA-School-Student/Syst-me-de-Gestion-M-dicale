package org.example.healthcare.controller;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.DossierRequest;
import org.example.healthcare.dto.DossierResponse;
import org.example.healthcare.service.DossierMedicalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dossier")
public class DossierMedicalController {

    private  final DossierMedicalService dossierMedicalService;


    @PostMapping
    public DossierResponse creerDossier(@RequestBody DossierRequest request){
        return dossierMedicalService.CreerDossier(request);
    }

    @GetMapping("/{id}")
    public DossierResponse consulterDossier(@PathVariable Long id){
        return dossierMedicalService.consulterDossier(id);
    }
}
