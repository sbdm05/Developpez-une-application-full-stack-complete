import { Topic } from './topic.model';

export interface Profile {
  id: number;
  email: string;
  username: string;
  subscriptions: Topic[];
}

export interface UpdateProfileRequest {
  username: string;
  email: string;
  password?: string; // absent ou vide = mot de passe inchangé
}