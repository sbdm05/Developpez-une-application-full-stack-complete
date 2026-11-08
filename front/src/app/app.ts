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
  // protected readonly title = signal('front');
  // test connexion back

constructor() {
      inject(AuthService)
        .login({ identifier: 'alice...', password: 'M...' })
        .subscribe({ next: () => console.log('Connecté'), error: (e: any) => console.log('Erreur', e.status) });
    }
  
}
