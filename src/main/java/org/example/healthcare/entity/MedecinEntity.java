


package org.example.healthcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medecin")
public class MedecinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String nom;
    private String specialite;
    private String email;
    private String telephone;

    @OneToMany(mappedBy = "medecin",cascade = CascadeType.ALL)
    List<RendezVousEntity> rendezVous;


}
