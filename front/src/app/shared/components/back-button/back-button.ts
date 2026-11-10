import { Component, input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MatIcon } from '@angular/material/icon';

@Component({
  imports: [RouterLink, MatIcon],
  selector: 'app-back-button',
  styleUrl: './back-button.scss',
  templateUrl: './back-button.html',
})
export class BackButton {
  // Page vers laquelle revenir (ex. "/" depuis Connexion)
  readonly link = input.required<string>();
}
