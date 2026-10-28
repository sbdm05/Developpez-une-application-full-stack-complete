package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCommentRequest(

        @NotBlank(message = "Le commentaire ne peut pas être vide")
        @Size(max = 2000, message = "Le commentaire ne doit pas dépasser 2 000 caractères")
        String content
) {
}