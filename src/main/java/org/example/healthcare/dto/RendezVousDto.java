package org.example.healthcare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "la date de rendez vous est obligatoire")
    private LocalDate dateRendezVous;
    @NotBlank(message = "le statut est obligatoire")
    private StatutRendezVous statut;

    @NotBlank(message = "le patentId est obligatoire")
    private Long patientId;
    @NotBlank(message = "le medecinId est obligatoire")
    private Long medecinId;

}
