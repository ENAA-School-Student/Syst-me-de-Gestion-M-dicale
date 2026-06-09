package org.example.healthcare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.healthcare.enums.StatutRendezVous;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Spliterator;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RendezVousDto implements Serializable {
    @NotNull(message = "la date de rendez vous est obligatoire")
    private LocalDate dateRendezVous;
    @NotNull(message = "le statut est obligatoire")
    private StatutRendezVous statut;

    @NotNull(message = "le patentId est obligatoire")
    private Long patientId;
    @NotNull(message = "le medecinId est obligatoire")
    private Long medecinId;

}
