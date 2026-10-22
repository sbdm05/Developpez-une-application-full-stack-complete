package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateProfileRequest(

        @NotBlank(message = "L'e-mail est obligatoire")
        @Email(message = "L'e-mail n'est pas valide")
        @Size(max = 255, message = "L'e-mail est trop long")
        String email,

        @NotBlank(message = "Le nom d'utilisateur est obligatoire")
        @Size(max = 50, message = "Le nom d'utilisateur ne doit pas dépasser 50 caractères")
        String username,

        // Facultatif : null ou vide = mot de passe inchangé
        @Pattern(regexp = PasswordPolicy.OPTIONAL_REGEX, message = PasswordPolicy.MESSAGE)
        String password
) {
}