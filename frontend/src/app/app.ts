import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { CarritoComponent } from './cliente/carrito/carrito';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, CarritoComponent],
  templateUrl: './app.html',
  styleUrls: ['./app.scss'] // 👈 plural, no "styleUrl"
})
export class App {
  protected readonly title = signal('frontend');
}
