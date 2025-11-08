import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

interface Cliente {
  id: number;
  nombreCompleto: string;
  tipoDocumento: string;
  numeroDocumento: string;
  email: string;
  telefono: string;
  estado: 'Activo' | 'Inactivo';
}

@Component({
  selector: 'app-clientes',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './clientes.html',
  styleUrls: ['./clientes.scss']
})
export class ClientesComponent {
  clientes: Cliente[] = [
    { id: 1, nombreCompleto: 'Juan Pérez', tipoDocumento: 'DNI', numeroDocumento: '12345678', email: 'juan@gmail.com', telefono: '987654321', estado: 'Activo' },
    { id: 2, nombreCompleto: 'María Gómez', tipoDocumento: 'RUC', numeroDocumento: '20452369874', email: 'maria@empresa.com', telefono: '998877665', estado: 'Inactivo' }
  ];

  // filtros
  buscar = '';
  filtroTipo = '';
  filtroEstado = '';

  // formulario modal
  clienteActual: Cliente = this.nuevoCliente();
  editando = false;

  // filtrar resultados
  get clientesFiltrados(): Cliente[] {
    return this.clientes.filter(c => {
      const matchBuscar =
        c.nombreCompleto.toLowerCase().includes(this.buscar.toLowerCase()) ||
        c.numeroDocumento.includes(this.buscar);
      const matchTipo = !this.filtroTipo || c.tipoDocumento === this.filtroTipo;
      const matchEstado = !this.filtroEstado || c.estado === this.filtroEstado;
      return matchBuscar && matchTipo && matchEstado;
    });
  }

  nuevoCliente(): Cliente {
    return {
      id: 0,
      nombreCompleto: '',
      tipoDocumento: '',
      numeroDocumento: '',
      email: '',
      telefono: '',
      estado: 'Activo'
    };
  }

  limpiarFormulario() {
    this.clienteActual = this.nuevoCliente();
    this.editando = false;
  }

  guardarCliente() {
    if (!this.clienteActual.nombreCompleto || !this.clienteActual.numeroDocumento) {
      alert('Por favor completa los campos obligatorios.');
      return;
    }

    if (this.editando) {
      const idx = this.clientes.findIndex(c => c.id === this.clienteActual.id);
      if (idx >= 0) this.clientes[idx] = { ...this.clienteActual };
    } else {
      const nuevoId = Math.max(...this.clientes.map(c => c.id), 0) + 1;
      this.clientes.push({ ...this.clienteActual, id: nuevoId });
    }

    this.limpiarFormulario();
    (document.getElementById('cerrarModalBtn') as HTMLButtonElement)?.click();
  }

  editarCliente(c: Cliente) {
    this.clienteActual = { ...c };
    this.editando = true;
    const modal = document.getElementById('modalCliente');
    if (modal) new (window as any).bootstrap.Modal(modal).show();
  }

  eliminarCliente(id: number) {
    if (confirm('¿Está seguro de eliminar este cliente?')) {
      this.clientes = this.clientes.filter(c => c.id !== id);
    }
  }
}
