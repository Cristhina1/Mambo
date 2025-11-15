import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CarritoService } from '../../services/carrito.service';
import { ItemCarrito } from '../../services/carrito.service';
import { CarritoComponent } from '../../cliente/carrito/carrito';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterModule,CarritoComponent],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  isLoggedIn = false;
  userName: string | null = null;

  carritoItems: ItemCarrito[] = [];

  constructor(public carritoService: CarritoService) {}

  ngOnInit() {
    this.carritoService.carrito$.subscribe(items => {
      this.carritoItems = items;
    });
  }

  logout() {
    this.isLoggedIn = false;
    this.userName = null;
  }

  login(name: string) {
    this.isLoggedIn = true;
    this.userName = name;
  }
}
