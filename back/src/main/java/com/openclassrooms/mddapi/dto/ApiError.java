package com.openclassrooms.mddapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

/** Format unique des réponses d'erreur de l'API. */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiError(int status, String message, Map<String, String> errors) {

    public ApiError(int status, String message) {
        this(status, message, null);
    }
}