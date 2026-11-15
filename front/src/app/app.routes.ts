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
 // Pages connectées : affichées dans MainLayout (header + navigation)
  {
    path: '',
    canActivate: [authGuard],
    loadComponent: () => import('./layout/main-layout/main-layout').then((m) => m.MainLayout),
    children: [
      {
        path: 'feed',
        title: 'MDD - Articles',
        loadComponent: () => import('./pages/feed/feed').then((m) => m.Feed),
      },
      {
        path: 'topics',
        title: 'MDD - Thèmes',
        loadComponent: () => import('./pages/topics/topics').then((m) => m.Topics),
      },
      {
        path: 'profile',
        title: 'MDD - Profil',
        loadComponent: () => import('./pages/profile/profile').then((m) => m.Profile),
      },
    ],
  },


  // Toute URL inconnue renvoie à l'accueil
  { path: '**', redirectTo: '' },
];