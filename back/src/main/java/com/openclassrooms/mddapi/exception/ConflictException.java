package com.openclassrooms.mddapi.exception;

/** Une donnée existe déjà (e-mail, nom d'utilisateur...). Traduite en 409. */
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}