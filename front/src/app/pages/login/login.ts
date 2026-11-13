import { Component, inject, signal } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AuthService } from '../../core/services/auth.service';
import { ApiError } from '../../core/models/api-error.model';
import { Header } from '../../shared/components/header/header';
import { BackButton } from '../../shared/components/back-button/back-button';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, MatButtonModule, MatFormFieldModule, MatInputModule, Header, BackButton],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {
  private readonly fb = inject(NonNullableFormBuilder);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  protected readonly submitting = signal(false);
  protected readonly errorMessage = signal<string | null>(null);

  // Mêmes règles que LoginRequest côté back : deux champs obligatoires, sans format imposé
  protected readonly form = this.fb.group({
    identifier: ['', Validators.required],
    password: ['', Validators.required],
  });

  protected submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.submitting.set(true);
    this.errorMessage.set(null);

    this.authService.login(this.form.getRawValue()).subscribe({
      next: () => this.router.navigate(['/feed']), // token déjà enregistré par AuthService
      error: (error: HttpErrorResponse) => {
        this.submitting.set(false);
        this.handleError(error);
      },
    });
  }

  private handleError(error: HttpErrorResponse): void {
    const apiError = error.error as ApiError | null;

    // Back injoignable
    if (error.status === 0) {
      this.errorMessage.set('Impossible de contacter le serveur. Veuillez réessayer.');
      return;
    }

    // 401 (identifiants refusés), 400 et autres : message du back
    this.errorMessage.set(apiError?.message ?? 'Une erreur est survenue. Veuillez réessayer.');
  }
}