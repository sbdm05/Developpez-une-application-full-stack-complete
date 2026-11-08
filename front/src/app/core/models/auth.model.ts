export interface LoginRequest {
  identifier: string; // e-mail ou nom d'utilisateur
  password: string;
}

export interface RegisterRequest {
  username: string;
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
}