import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';
import { guestGuard } from './core/guards/guest.guard';

export const routes: Routes = [
  // Pages publiques
  {
    path: '',
    title: 'MDD',
    canActivate: [guestGuard],
    loadComponent: () => import('./pages/home/home').then((m) => m.Home),
  },
  {
    path: 'login',
    title: 'MDD - Connexion',
    canActivate: [guestGuard],
    loadComponent: () => import('./pages/login/login').then((m) => m.Login),
  },
  {
    path: 'register',
    title: 'MDD - Inscription',
    canActivate: [guestGuard],
    loadComponent: () => import('./pages/register/register').then((m) => m.Register),
  },

  // Pages connectées
  {
    path: 'feed',
    title: 'MDD - Articles',
    canActivate: [authGuard],
    loadComponent: () => import('./pages/feed/feed').then((m) => m.Feed),
  },

  // Toute URL inconnue renvoie à l'accueil
  { path: '**', redirectTo: '' },
];