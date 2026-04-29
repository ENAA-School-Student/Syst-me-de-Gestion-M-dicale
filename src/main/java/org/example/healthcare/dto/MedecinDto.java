package org.example.healthcare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedecinDto {
     private Long id;
    @NotBlank(message = "le nom est  obligatoire")
    private String nom;
    @NotBlank(message = "la specialiter est obligatoire")
    private String specialite;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email invalide")
    private String email;

    @NotNull(message = "le telephone est obligatoire")
    private String telephone;
}
