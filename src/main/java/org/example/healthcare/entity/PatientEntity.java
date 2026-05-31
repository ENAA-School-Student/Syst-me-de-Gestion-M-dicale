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
@PrimaryKeyJoinColumn(name = "id")
public class PatientEntity extends UserEntity {


    private String prenom;
    private String telephone;
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    List<RendezVousEntity> rendezVousEntities;

    @OneToOne(mappedBy = "patient",cascade = CascadeType.ALL)
    private DossierMedicalEntity dossierMedicalEntities;

}
