package com.openclassrooms.mddapi.exception;

/** La ressource demandée n'existe pas. Traduite en 404. */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}