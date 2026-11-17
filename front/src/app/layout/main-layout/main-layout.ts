import { Component, inject, signal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { BreakpointObserver } from '@angular/cdk/layout';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { AuthService } from '../../core/services/auth.service';
import { MenuSideNav } from '../menu-side-nav/menu-side-nav';
import { NAV_LINKS } from './nav-links';

@Component({
  selector: 'app-main-layout',
  imports: [
    RouterOutlet,
    RouterLink,
    RouterLinkActive,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MenuSideNav,
  ],
  templateUrl: './main-layout.html',
  styleUrl: './main-layout.scss',
})
export class MainLayout {
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  protected readonly navLinks = NAV_LINKS;

  // Panneau mobile ouvert ou fermé
  protected readonly menuOpen = signal(false);

  constructor() {
    // Si l'écran repasse en largeur desktop, on ferme le panneau mobile
    inject(BreakpointObserver)
      .observe('(min-width: 600px)')
      .pipe(takeUntilDestroyed())
      .subscribe((state) => {
        if (state.matches) {
          this.menuOpen.set(false);
        }
      });
  }

  protected openMenu(): void {
    this.menuOpen.set(true);
  }

  protected closeMenu(): void {
    this.menuOpen.set(false);
  }

  protected logout(): void {
    this.menuOpen.set(false);
    this.authService.logout();
    this.router.navigate(['/']);
  }
}