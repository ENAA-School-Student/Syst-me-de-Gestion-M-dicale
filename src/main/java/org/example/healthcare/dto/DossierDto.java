package org.example.healthcare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DossierDto implements Serializable {
    private Long id;
    @NotBlank(message = "L'diagnostic est obligatoire")
    private String diagnostic;
    @NotBlank(message = "l' observation est obligatoire")
    private String observations;

    @NotNull(message = "la date de creation est obligatoire")
    private LocalDate dateCreation;
    @NotNull(message = "le patient id est obligatoire")
    private Long patientId;
    private PatientDto patient;
}
