import { Component, inject } from '@angular/core';
import { Router, RouterOutlet, RouterLinkWithHref } from '@angular/router';
import { NAV_LINKS } from './nav-links';
import { AuthService } from '../../core/services/auth.service';
import { MatIcon } from '@angular/material/icon';
@Component({
  imports: [RouterOutlet, RouterLinkWithHref, MatIcon],
  selector: 'app-main-layout',
  styleUrl: './main-layout.scss',
  templateUrl: './main-layout.html',
})
export class MainLayout {
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  protected readonly navLinks = NAV_LINKS;

  protected logout(): void {
    this.authService.logout();
    this.router.navigate(['/']);
  }

}
