import { Component, Inject, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { AuthService } from './core/services/auth.service';

@Component({
  imports: [RouterOutlet],
  selector: 'app-root',
  styleUrl: './app.scss',
  templateUrl: './app.html',
})
export class App {
private readonly http = inject(HttpClient);
  private readonly authService = inject(AuthService);

  constructor() {
    // ici on vérifie si présence de token
    if (!this.authService.isLoggedIn()) {
      this.authService
        .login({ identifier: 'alice', password: 'Mµµµµµ!' })
        .subscribe(() => this.loadTopics());
    } else {
      this.loadTopics();
    }
  }

  private loadTopics(): void {
    this.http.get(`${environment.apiUrl}/topics`).subscribe({
      next: (topics) => console.log('Topics', topics),
      error: (e) => console.log('Erreur', e.status),
    });
  }
  
}
