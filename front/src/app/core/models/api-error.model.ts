export interface ApiError {
  status: number;
  message: string;
  errors?: Record<string, string>; // erreurs de validation par champ
}