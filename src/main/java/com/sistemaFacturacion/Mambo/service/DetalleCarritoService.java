package com.sistemaFacturacion.Mambo.service;

import com.sistemaFacturacion.Mambo.model.carrito;
import com.sistemaFacturacion.Mambo.model.detalleCarrito;
import com.sistemaFacturacion.Mambo.repository.CarritoRepository;
import com.sistemaFacturacion.Mambo.repository.DetalleCarritoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleCarritoService {

    private final DetalleCarritoRepository detalleCarritoRepository;
    private final CarritoRepository carritoRepository;

    public DetalleCarritoService(DetalleCarritoRepository detalleCarritoRepository,
                                 CarritoRepository carritoRepository) {
        this.detalleCarritoRepository = detalleCarritoRepository;
        this.carritoRepository = carritoRepository;
    }

    // ✅ Recalcular total del carrito
    private void actualizarTotalCarrito(carrito carrito) {
        double total = carrito.getDetalles().stream()
                .mapToDouble(detalleCarrito::getSubtotal)
                .sum();
        // Guardamos el carrito actualizado
        carritoRepository.save(carrito);
    }

    // Crear detalle
    public detalleCarrito crearDetalle(detalleCarrito detalle) {
        detalleCarrito nuevo = detalleCarritoRepository.save(detalle);
        actualizarTotalCarrito(nuevo.getCarrito());
        return nuevo;
    }

    // Actualizar detalle
    public detalleCarrito actualizarDetalle(Long id, detalleCarrito detalle) {
        return detalleCarritoRepository.findById(id).map(existing -> {
            existing.setProducto(detalle.getProducto());
            existing.setCantidad(detalle.getCantidad());
            existing.setCarrito(detalle.getCarrito());
            detalleCarrito actualizado = detalleCarritoRepository.save(existing);
            actualizarTotalCarrito(actualizado.getCarrito());
            return actualizado;
        }).orElseThrow(() -> new RuntimeException("Detalle de carrito no encontrado con ID: " + id));
    }

    // Eliminar detalle
    public void eliminarDetalle(Long id) {
        detalleCarritoRepository.findById(id).ifPresent(detalle -> {
            carrito carrito = detalle.getCarrito();
            detalleCarritoRepository.deleteById(id);
            actualizarTotalCarrito(carrito);
        });
    }

    // Buscar por ID
    public Optional<detalleCarrito> obtenerPorId(Long id) {
        return detalleCarritoRepository.findById(id);
    }

    // Listar todos
    public List<detalleCarrito> listarDetalles() {
        return detalleCarritoRepository.findAll();
    }

    // Listar por carrito
    public List<detalleCarrito> listarPorCarrito(Long carritoId) {
        return detalleCarritoRepository.findByCarritoId(carritoId);
    }

    // Listar por producto
    public List<detalleCarrito> listarPorProducto(Long productoId) {
        return detalleCarritoRepository.findByProductoId(productoId);
    }
}
