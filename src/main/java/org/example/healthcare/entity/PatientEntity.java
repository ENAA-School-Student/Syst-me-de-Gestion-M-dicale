package org.example.healthcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    List<RendezVousEntity> rendezVousEntities;

    @OneToOne(mappedBy = "patient",cascade = CascadeType.ALL)
    private DossierMedicalEntity dossierMedicalEntities;

}
