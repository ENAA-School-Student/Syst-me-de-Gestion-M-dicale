package org.example.healthcare.controller;


import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.RendezVousDto;
import org.example.healthcare.service.RendezVousService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rendezVous")
public class RendezVousController {

    private  final RendezVousService rendezVousService;

    @PostMapping
    public RendezVousDto creerRendezVous(@RequestBody RendezVousDto dto){
        return rendezVousService.creerRendezVous(dto);
    }

    @PutMapping("/{id}")
    public RendezVousDto modifierRendezVous(@PathVariable Long id){
        return rendezVousService.modifierRendezVous(id);
    }
    @GetMapping
    public List<RendezVousDto> listerRendezVous(){
        return rendezVousService.listerRendezVous();
    }
    @PatchMapping("/{id}/annuler")
    public RendezVousDto annullerRendezVous(@PathVariable Long id){
        return rendezVousService.AnnuleRendezVous(id);
    }
    @GetMapping("/{id}")
    public List<RendezVousDto> chercherPatient(@PathVariable Long patientId){
        return rendezVousService.chercherPatient(patientId);
    }
    @GetMapping("/{id}")
    public List<RendezVousDto> chercherMedecin(@PathVariable Long medecinId){
        return rendezVousService.chercherMedecin(medecinId);
    }
}


