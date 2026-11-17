import { Component, inject } from '@angular/core';
import { AuthService } from '../../core/services/auth.service';
import { Router } from '@angular/router';
import { MatButton, MatButtonModule } from '@angular/material/button';

@Component({
  imports: [MatButtonModule],
  selector: 'app-feed',
  styleUrl: './feed.scss',
  templateUrl: './feed.html',
})
export class Feed {


}
