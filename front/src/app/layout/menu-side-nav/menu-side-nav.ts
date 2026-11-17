import { Component, output } from '@angular/core';
import { NAV_LINKS } from '../main-layout/nav-links';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { MatIcon } from '@angular/material/icon';

@Component({
  imports: [RouterLink, RouterLinkActive, MatIcon],
  selector: 'app-menu-side-nav',
  styleUrl: './menu-side-nav.scss',
  templateUrl: './menu-side-nav.html',
})
export class MenuSideNav {
  protected readonly navLinks = NAV_LINKS;

  // Prévient le layout : un lien a été cliqué (pour fermer le panneau)
  readonly navigate = output<void>();

  // Prévient le layout : l'utilisateur veut se déconnecter
  readonly logout = output<void>();
}
