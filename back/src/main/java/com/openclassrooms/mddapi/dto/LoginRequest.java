package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank(message = "L'e-mail ou le nom d'utilisateur est obligatoire")
        String identifier,

        @NotBlank(message = "Le mot de passe est obligatoire")
        String password
) {
}