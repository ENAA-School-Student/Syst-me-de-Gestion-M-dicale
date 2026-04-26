package org.example.healthcare.controller;

import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.MedecinDto;
import org.example.healthcare.service.MedecinService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecin")
@RequiredArgsConstructor
public class MedecinController {

    private final MedecinService medecinService;

    @PostMapping
    public MedecinDto ajoutermedecin(MedecinDto dto){
        return medecinService.ajouterMedecin(dto);
    }
    @PutMapping("/{id}")
    public MedecinDto modifiermedecin(@PathVariable Long id,@RequestBody MedecinDto dto){
        return medecinService.modifierMedecin(id,dto);
    }
    @DeleteMapping("/{id}")
    public void supprimerMedecin(@PathVariable Long id){
        medecinService.supprimerMedecin(id);
    }
    @GetMapping
    public List<MedecinDto> listerMedecins(){
        return medecinService.listerMedecins();
    }
}
