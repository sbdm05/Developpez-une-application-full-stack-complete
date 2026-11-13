import { Component, inject, signal } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AuthService } from '../../core/services/auth.service';
import { ApiError } from '../../core/models/api-error.model';
import { PASSWORD_MESSAGE, PASSWORD_PATTERN } from '../../core/validators/password-policy';
import { Header } from '../../shared/components/header/header';
import { BackButton } from '../../shared/components/back-button/back-button';
@Component({
 imports: [ReactiveFormsModule, MatButtonModule, MatFormFieldModule, MatInputModule, Header, BackButton],
  selector: 'app-register',
  styleUrl: './register.scss',
  templateUrl: './register.html',
})
export class Register {
  private readonly fb = inject(NonNullableFormBuilder);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  protected readonly passwordMessage = PASSWORD_MESSAGE;
  protected readonly submitting = signal(false);
  protected readonly errorMessage = signal<string | null>(null);

  // Mêmes règles que RegisterRequest côté back
  protected readonly form = this.fb.group({
    username: ['', [Validators.required, Validators.maxLength(50)]],
    email: ['', [Validators.required, Validators.email, Validators.maxLength(255)]],
    password: ['', [Validators.required, Validators.pattern(PASSWORD_PATTERN)]],
  });

  protected submit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched(); // affiche les erreurs des champs non touchés
      return;
    }

    this.submitting.set(true);
    this.errorMessage.set(null);

    this.authService.register(this.form.getRawValue()).subscribe({
      next: () => this.router.navigate(['/feed']), // token déjà enregistré par AuthService
      error: (error: HttpErrorResponse) => {
        this.submitting.set(false);
        this.handleError(error);
      },
    });
  }

  private handleError(error: HttpErrorResponse): void {
    const apiError = error.error as ApiError | null;

    // 400 : erreurs de validation du back, affichées sous chaque champ concerné
    if (error.status === 400 && apiError?.errors) {
      for (const [field, message] of Object.entries(apiError.errors)) {
        this.form.get(field)?.setErrors({ server: message });
      }
      return;
    }

    // Back injoignable
    if (error.status === 0) {
      this.errorMessage.set('Impossible de contacter le serveur. Veuillez réessayer.');
      return;
    }

    // 409 (e-mail ou nom déjà utilisé) et autres erreurs : message du back
    this.errorMessage.set(apiError?.message ?? 'Une erreur est survenue. Veuillez réessayer.');
  }
}
