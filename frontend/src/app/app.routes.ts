import { Routes } from '@angular/router';
import { InicioComponent } from './cliente/inicio/inicio.component';
import { ListaProductosComponent } from './cliente/lista-productos/lista-productos.component';
import { HistorialCompraComponent } from './cliente/historial-compra/historial-compra.component';
import { ContactanosComponent} from './cliente/contactanos/contactanos.component';
import { CheckoutLayoutComponent } from './checkout/checkout-layout/checkout-layout.component';
import { DatosPersonalesComponent } from './checkout/datos-personales/datos-personales.component';
import { TipoDespachoComponent } from './checkout/tipo-despacho/tipo-despacho.component';
import { MetodoPagoComponent } from './checkout/metodo-pago/metodo-pago.component';


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
  { path: 'admin/vendedores', component: VendedoresComponent },

  { path: '', redirectTo: '/productos', pathMatch: 'full' },
  { path: 'inicio', component: InicioComponent },
  { path: 'productos', component: ListaProductosComponent },
  { path: 'historial', component: HistorialCompraComponent },
  {path: 'contactanos', component: ContactanosComponent},
  {
    path: 'checkout',
    component: CheckoutLayoutComponent,
    children: [
      { path: '', redirectTo: 'datos', pathMatch: 'full' },
      { path: 'datos', component: DatosPersonalesComponent },
      { path: 'despacho', component: TipoDespachoComponent },
      { path: 'pago', component: MetodoPagoComponent }
    ]
  },
  { path: '**', redirectTo: '/productos' },
];
