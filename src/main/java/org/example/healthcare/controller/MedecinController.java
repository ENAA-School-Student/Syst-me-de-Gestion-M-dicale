package org.example.healthcare.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.dto.MedecinDto;
import org.example.healthcare.dto.MedecinRequestDto;
import org.example.healthcare.service.MedecinService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medecin")
@RequiredArgsConstructor
@Tag(name = "Gestion des Médecins")
public class MedecinController {

    private final MedecinService medecinService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Ajouter medecin")
    public ResponseEntity<MedecinRequestDto> ajoutermedecin(@Valid @RequestBody  MedecinRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(medecinService.ajouterMedecin(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Modifier medecin")
    public ResponseEntity<MedecinDto> modifiermedecin( @PathVariable Long id,@Valid @RequestBody MedecinDto dto){
        return ResponseEntity.ok(medecinService.modifierMedecin(id,dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "supprimer medecin")
    public ResponseEntity<Void>supprimerMedecin(@PathVariable Long id){
        medecinService.supprimerMedecin(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "lister medecin")
    public ResponseEntity<Page<MedecinDto>> listerMedecins(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "5")int size,
            @RequestParam(defaultValue = "asc")String sorDir

    ){
        return ResponseEntity.ok( medecinService.listerMedecins(page,size,sorDir));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<Page<MedecinDto>> rechercherMedecin(
            @RequestParam String specialite,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(
                medecinService.rechercherParSpecialite(specialite, page, size)
        );
    }
}
