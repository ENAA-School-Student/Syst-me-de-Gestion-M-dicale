package org.example.healthcare.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.MedecinDto;
import org.example.healthcare.service.MedecinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecin")
@RequiredArgsConstructor
@Tag(name = "Gestion des Médecins")
public class MedecinController {

    private final MedecinService medecinService;

    @PostMapping
    public ResponseEntity<MedecinDto> ajoutermedecin(@Valid @RequestBody  MedecinDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(medecinService.ajouterMedecin(dto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<MedecinDto> modifiermedecin( @PathVariable Long id,@Valid @RequestBody MedecinDto dto){
        return ResponseEntity.ok(medecinService.modifierMedecin(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>supprimerMedecin(@PathVariable Long id){
        medecinService.supprimerMedecin(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping
    public List<MedecinDto> listerMedecins(){
        return medecinService.listerMedecins();
    }
}
