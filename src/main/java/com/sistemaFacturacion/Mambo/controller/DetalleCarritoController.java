package com.sistemaFacturacion.Mambo.controller;

import com.sistemaFacturacion.Mambo.model.detalleCarrito;
import com.sistemaFacturacion.Mambo.service.DetalleCarritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-carrito")
public class DetalleCarritoController {

    private final DetalleCarritoService detalleCarritoService;

    public DetalleCarritoController(DetalleCarritoService detalleCarritoService) {
        this.detalleCarritoService = detalleCarritoService;
    }

    // Crear detalle en carrito
    @PostMapping
    public ResponseEntity<detalleCarrito> crear(@RequestBody detalleCarrito detalle) {
        return ResponseEntity.ok(detalleCarritoService.crearDetalle(detalle));
    }

    // Actualizar detalle
    @PutMapping("/{id}")
    public ResponseEntity<detalleCarrito> actualizar(@PathVariable Long id, @RequestBody detalleCarrito detalle) {
        return ResponseEntity.ok(detalleCarritoService.actualizarDetalle(id, detalle));
    }

    // Eliminar detalle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        detalleCarritoService.eliminarDetalle(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener detalle por ID
    @GetMapping("/{id}")
    public ResponseEntity<detalleCarrito> obtenerPorId(@PathVariable Long id) {
        return detalleCarritoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar todos los detalles
    @GetMapping
    public ResponseEntity<List<detalleCarrito>> listar() {
        return ResponseEntity.ok(detalleCarritoService.listarDetalles());
    }

    // Listar detalles por carrito
    @GetMapping("/carrito/{carritoId}")
    public ResponseEntity<List<detalleCarrito>> listarPorCarrito(@PathVariable Long carritoId) {
        return ResponseEntity.ok(detalleCarritoService.listarPorCarrito(carritoId));
    }

    // Listar detalles por producto
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<detalleCarrito>> listarPorProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(detalleCarritoService.listarPorProducto(productoId));
    }
}
