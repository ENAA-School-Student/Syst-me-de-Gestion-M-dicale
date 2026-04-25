
package org.example.healthcare.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.healthcare.enums.StatutRendezVous;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "rendez_vous")
public class RendezVousEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateRendezVous;
    @Enumerated(EnumType.STRING)
    private StatutRendezVous status;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patientEntities;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private MedecinEntity medecinEntities;
}
