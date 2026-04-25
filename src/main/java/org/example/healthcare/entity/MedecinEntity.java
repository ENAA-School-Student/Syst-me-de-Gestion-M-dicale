


package org.example.healthcare.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "medecin")
public class MedecinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nom;
    private String specialite;
    private String email;
    private String telephone;

    @OneToMany(mappedBy = "rendezVous",cascade = CascadeType.ALL)
    List<RendezVousEntity> rendezVousEntities;

    @OneToMany(mappedBy = "medecin",cascade = CascadeType.ALL)
    List<DossierMedicalEntity> dossierMedicalEntities ;

}
