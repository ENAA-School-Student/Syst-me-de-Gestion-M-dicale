package org.example.healthcare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.healthcare.enums.Role;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PatientRequestDto {
    private Long id;
    @NotBlank(message = "le nom est obligatoire")
    private String username;

    @NotBlank(message = "le prenom est obligatoire")
    private String prenom;

    @NotBlank(message = "le password est obligatoire")
    private String password;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email invalide")
    private String email;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    @NotNull(message = "la date de naissance est obligatoire")
    private LocalDate dateNaissance;

    private Role role = Role.PATIENT;
}