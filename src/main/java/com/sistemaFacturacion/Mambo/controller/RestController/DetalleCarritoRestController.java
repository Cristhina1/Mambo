package com.sistemaFacturacion.Mambo.controller.RestController;


import com.sistemaFacturacion.Mambo.Service.DetalleCompraService;
import com.sistemaFacturacion.Mambo.dto.DetalleCarritoDto;
import com.sistemaFacturacion.Mambo.model.detalleCarrito;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/detalles-carrito")
@CrossOrigin(origins = "*")
public class DetalleCarritoRestController {

    private final DetalleCompraService detalleCarritoService;

    public DetalleCarritoRestController(DetalleCompraService detalleCarritoService) {
        this.detalleCarritoService = detalleCarritoService;
    }

    // ➕ Crear un detalle
    @PostMapping
    public ResponseEntity<DetalleCompraDto> crearDetalle(@RequestBody detalleCarrito detalle) {
        DetalleCompraDto nuevoDetalle = detalleCarritoService.crearDetalle(detalle);
        return ResponseEntity.ok(nuevoDetalle);
    }

    // ✏️ Actualizar un detalle existente
    @PutMapping("/{id}")
    public ResponseEntity<DetalleCompraDto> actualizarDetalle(@PathVariable Long id, @RequestBody detalleCarrito detalle) {
        DetalleCompraDto actualizado = detalleCarritoService.actualizarDetalle(id, detalle);
        return ResponseEntity.ok(actualizado);
    }

    // ❌ Eliminar un detalle por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long id) {
        detalleCarritoService.eliminarDetalle(id);
        return ResponseEntity.noContent().build();
    }

    // 🔎 Obtener un detalle por ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalleCompraDto> obtenerPorId(@PathVariable Long id) {
        Optional<DetalleCompraDto> detalle = detalleCarritoService.obtenerPorId(id);
        return detalle.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // 📋 Listar todos los detalles
    @GetMapping
    public ResponseEntity<List<DetalleCompraDto>> listarDetalles() {
        return ResponseEntity.ok(detalleCarritoService.listarDetalles());
    }

    // 🔍 Listar detalles por carrito
    @GetMapping("/carrito/{carritoId}")
    public ResponseEntity<List<DetalleCompraDto>> listarPorCarrito(@PathVariable Long carritoId) {
        return ResponseEntity.ok(detalleCarritoService.listarPorCarrito(carritoId));
    }

    // 🔍 Listar detalles por producto
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<DetalleCompraDto>> listarPorProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(detalleCarritoService.listarPorProducto(productoId));
    }
}
