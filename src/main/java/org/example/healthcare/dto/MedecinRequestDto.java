package org.example.healthcare.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.healthcare.enums.Role;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MedecinRequestDto {
    private Long id;
    @NotBlank(message = "le nom est  obligatoire")
    private String username;
    @NotBlank(message = "la specialiter est obligatoire")
    private String specialite;

    @NotBlank(message = "le password est obligatoire")
    private String password;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email invalide")
    private String email;

    @NotNull(message = "le telephone est obligatoire")
    private String telephone;

    private Role role=Role.MEDECIN;
}
