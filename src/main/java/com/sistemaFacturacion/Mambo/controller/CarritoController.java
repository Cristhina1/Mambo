package com.sistemaFacturacion.Mambo.controller;

import com.sistemaFacturacion.Mambo.model.carrito;
import com.sistemaFacturacion.Mambo.model.detalleCarrito;
import com.sistemaFacturacion.Mambo.service.CarritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carritos")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    // ➕ Crear carrito
    @PostMapping
    public carrito crearCarrito(@RequestBody carrito carrito) {
        return carritoService.crearCarrito(carrito);
    }

    // ✏️ Actualizar carrito
    @PutMapping("/{id}")
    public carrito actualizarCarrito(@PathVariable Long id, @RequestBody carrito carrito) {
        return carritoService.actualizarCarrito(id, carrito);
    }

    // 🔎 Obtener carrito por id
    @GetMapping("/{id}")
    public ResponseEntity<carrito> obtenerCarrito(@PathVariable Long id) {
        return carritoService.obtenerCarritoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 📋 Listar todos los carritos
    @GetMapping
    public List<carrito> listarCarritos() {
        return carritoService.listarCarritos();
    }

    // ❌ Eliminar carrito
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long id) {
        carritoService.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    }

    // 🔎 Buscar carritos por cliente
    @GetMapping("/cliente/{clienteId}")
    public List<carrito> buscarPorCliente(@PathVariable Long clienteId) {
        return carritoService.buscarPorCliente(clienteId);
    }

    // 🔎 Buscar carrito por comprobante
    @GetMapping("/comprobante/{comprobanteId}")
    public carrito buscarPorComprobante(@PathVariable Long comprobanteId) {
        return carritoService.buscarPorComprobante(comprobanteId);
    }

    // ➕ Agregar detalle a un carrito
    @PostMapping("/{carritoId}/detalles")
    public carrito agregarDetalle(@PathVariable Long carritoId, @RequestBody detalleCarrito detalle) {
        return carritoService.agregarDetalle(carritoId, detalle);
    }
}
