import { Routes } from '@angular/router';

import { HomeComponent } from './admin/home/home.component';
import { BoletaComponent } from './admin/boleta/boleta';
import { ClientesComponent } from './admin/clientes/clientes';
import { FacturaComponent } from './admin/factura/factura';
import { ListUsuariosComponent } from './admin/list-usuarios/list-usuarios';
import { ProductosComponent } from './admin/productos/productos';
import { ReporteComponent } from './admin/reporte/reporte';
import { VendedoresComponent } from './admin/vendedores/vendedores';

export const routes: Routes = [
  { path: '', redirectTo: 'admin/home', pathMatch: 'full' },
  { path: 'admin/home', component: HomeComponent },
  { path: 'admin/boleta', component: BoletaComponent },
  { path: 'admin/clientes', component: ClientesComponent },
  { path: 'admin/factura', component: FacturaComponent },
  { path: 'admin/list-usuarios', component: ListUsuariosComponent },
  { path: 'admin/productos', component: ProductosComponent },
  { path: 'admin/reporte', component: ReporteComponent },
  { path: 'admin/vendedores', component: VendedoresComponent }
];
