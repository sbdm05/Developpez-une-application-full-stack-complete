// Même règle que PasswordPolicy.java côté back :
// au moins 8 caractères, dont un chiffre, une minuscule, une majuscule et un caractère spécial
export const PASSWORD_PATTERN = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^A-Za-z0-9\s]).{8,}$/;

export const PASSWORD_MESSAGE =
  'Le mot de passe doit contenir au moins 8 caractères, dont un chiffre, ' +
  'une minuscule, une majuscule et un caractère spécial';