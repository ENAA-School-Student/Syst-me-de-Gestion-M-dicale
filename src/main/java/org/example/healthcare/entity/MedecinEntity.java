


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
@PrimaryKeyJoinColumn(name = "id")
public class MedecinEntity extends UserEntity {


    private String specialite;
    private String telephone;

    @OneToMany(mappedBy = "medecin",cascade = CascadeType.ALL)
    List<RendezVousEntity> rendezVous;


}
