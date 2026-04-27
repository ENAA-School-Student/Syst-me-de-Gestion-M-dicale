
package org.example.healthcare.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "dossier_medical")
public class DossierMedicalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String diagnostic;
    private String observations;
    private LocalDate dateCreation;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;


}
