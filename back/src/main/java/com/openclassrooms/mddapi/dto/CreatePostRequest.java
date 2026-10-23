package com.openclassrooms.mddapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePostRequest(

        @NotNull(message = "Le thème est obligatoire")
        Long topicId,

        @NotBlank(message = "Le titre est obligatoire")
        @Size(max = 255, message = "Le titre ne doit pas dépasser 255 caractères")
        String title,

        @NotBlank(message = "Le contenu est obligatoire")
        @Size(max = 10000, message = "Le contenu ne doit pas dépasser 10 000 caractères")
        String content
) {
}