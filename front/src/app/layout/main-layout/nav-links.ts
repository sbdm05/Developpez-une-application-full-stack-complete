export interface NavLink {
  label: string;
  path: string;
}

// Liens texte de la navigation, partagés par le header desktop et le menu mobile (MenuSideNav)
export const NAV_LINKS: NavLink[] = [
  { label: 'Articles', path: '/feed' },
  { label: 'Thèmes', path: '/topics' },
];