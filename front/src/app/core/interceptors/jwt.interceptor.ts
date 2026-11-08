import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { AuthService } from '../services/auth.service';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  const isApiRequest = req.url.startsWith(environment.apiUrl);
  const isAuthRequest = req.url.startsWith(`${environment.apiUrl}/auth/`);
  const token = authService.getToken();

  // Aller : ajoute le token aux requêtes de l'API, sauf login/register
  const request =
    token && isApiRequest && !isAuthRequest
      ? req.clone({ setHeaders: { Authorization: `Bearer ${token}` } })
      : req;

  // Retour : 401 sur une route protégée = token expiré ou invalide
  return next(request).pipe(
    catchError((error: unknown) => {
      if (error instanceof HttpErrorResponse && error.status === 401 && !isAuthRequest) {
        authService.logout();
        router.navigate(['/login']);
      }
      return throwError(() => error);
    }),
  );
};