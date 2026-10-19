package com.openclassrooms.mddapi.dto;

/** Règle de mot de passe des spécifications, partagée par l'inscription et la modification du profil. */
public final class PasswordPolicy {

    // Au moins 8 caractères, avec au moins : un chiffre, une minuscule, une majuscule, un caractère spécial
    public static final String REGEX =
            "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9\\s]).{8,}$";

    public static final String MESSAGE =
            "Le mot de passe doit contenir au moins 8 caractères, dont un chiffre, "
            + "une minuscule, une majuscule et un caractère spécial";

    private PasswordPolicy() {
    }
}