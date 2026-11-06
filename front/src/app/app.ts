import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';

@Component({
  imports: [RouterOutlet],
  selector: 'app-root',
  styleUrl: './app.scss',
  templateUrl: './app.html',
})
export class App {
  // protected readonly title = signal('front');
  // test connexion back

    // constructor() {
    //   inject(HttpClient)
    //     .get(`${environment.apiUrl}/topics`)
    //     .subscribe({ next: (r) => console.log('OK', r), error: (e) => console.log('Erreur', e.status) });
    // }
  
}
