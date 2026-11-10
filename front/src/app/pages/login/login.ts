import { Component } from '@angular/core';
import { Header } from '../../shared/components/header/header';
import { BackButton } from '../../shared/components/back-button/back-button';

@Component({
  imports: [Header, BackButton],
  selector: 'app-login',
  styleUrl: './login.scss',
  templateUrl: './login.html',
})
export class Login {}
