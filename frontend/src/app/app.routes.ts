import { Routes } from '@angular/router';

import { ListUsuariosComponent } from './admin/list-usuarios/list-usuarios';
import { ProductosComponent } from './admin/productos/productos';
import { ReporteComponent } from './admin/reporte/reporte';
import { VendedoresComponent } from './admin/vendedores/vendedores';

export const routes: Routes = [
  { path: '', redirectTo: 'admin/home', pathMatch: 'full' },
  { path: 'admin/list-usuarios', component: ListUsuariosComponent },
  { path: 'admin/productos', component: ProductosComponent },
  { path: 'admin/reporte', component: ReporteComponent },
  { path: 'admin/vendedores', component: VendedoresComponent }
];
