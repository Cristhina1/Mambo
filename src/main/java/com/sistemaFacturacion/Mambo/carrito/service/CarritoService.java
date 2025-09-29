package com.sistemaFacturacion.Mambo.carrito.service;

import com.sistemaFacturacion.Mambo.carrito.repository.CarritoRepository;
import com.sistemaFacturacion.Mambo.model.carrito;
import com.sistemaFacturacion.Mambo.model.detalleCarrito;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;

    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    // ➕ Crear carrito (con cliente y detalles)
    public carrito crearCarrito(carrito carrito) {
        // Al guardar el carrito, también se guardan sus detalles gracias al cascade
        return carritoRepository.save(carrito);
    }

    // ✏️ Actualizar carrito (ej: modificar detalles o cliente)
    public carrito actualizarCarrito(Long id, carrito carritoActualizado) {
        return carritoRepository.findById(id).map(carrito -> {
            carrito.setCliente(carritoActualizado.getCliente());
            carrito.setDetalles(carritoActualizado.getDetalles());
            return carritoRepository.save(carrito);
        }).orElseThrow(() -> new RuntimeException("carrito no encontrado con id " + id));
    }

    // 🔎 Buscar carrito por id
    public Optional<carrito> obtenerCarritoPorId(Long id) {
        return carritoRepository.findById(id);
    }

    // 📋 Listar todos los carritos
    public List<carrito> listarCarritos() {
        return carritoRepository.findAll();
    }

    // ❌ Eliminar carrito (se eliminan también sus detalles)
    public void eliminarCarrito(Long id) {
        carritoRepository.deleteById(id);
    }

    // 🔎 Buscar carritos de un cliente
    public List<carrito> buscarPorCliente(Long clienteId) {
        return carritoRepository.findByClienteId(clienteId);
    }

    // 🔎 Buscar carrito por comprobante
    public carrito buscarPorComprobante(Long comprobanteId) {
        return carritoRepository.findByComprobanteId(comprobanteId);
    }

    // ➕ Agregar detalle a un carrito existente
    public carrito agregarDetalle(Long carritoId, detalleCarrito detalle) {
        carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("carrito no encontrado con id " + carritoId));
        detalle.setCarrito(carrito);
        carrito.getDetalles().add(detalle);
        return carritoRepository.save(carrito);
    }
}
