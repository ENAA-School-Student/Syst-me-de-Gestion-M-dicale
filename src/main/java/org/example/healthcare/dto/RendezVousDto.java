package org.example.healthcare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.healthcare.enums.StatutRendezVous;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RendezVousDto {
    private LocalDate dateRendezVous;
    private StatutRendezVous statut;
    private Long patientId;
    private Long medecinId;

}
