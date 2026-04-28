package org.example.healthcare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DossierRequest {
    private String diagnostic;
    private String observations;
    private LocalDate dateCreation;
    private Long patientId;
}
