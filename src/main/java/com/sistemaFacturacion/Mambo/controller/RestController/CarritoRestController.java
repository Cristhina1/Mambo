package com.sistemaFacturacion.Mambo.controller.RestController;


import com.sistemaFacturacion.Mambo.Service.CarritoService;
import com.sistemaFacturacion.Mambo.dto.CarritoDTO;
import com.sistemaFacturacion.Mambo.dto.DetalleCarritoDto;
import com.sistemaFacturacion.Mambo.model.carrito;
import com.sistemaFacturacion.Mambo.model.detalleCarrito;
import com.sistemaFacturacion.Mambo.model.pago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carritos")
@CrossOrigin(origins = "*")
public class CarritoRestController {

    @Autowired
    private CarritoService carritoService;

    // ➕ Crear carrito nuevo
    @PostMapping
    public ResponseEntity<CarritoDTO> crearCarrito(@RequestBody carrito carrito) {
        CarritoDTO nuevoCarrito = carritoService.crearCarrito(carrito);
        return ResponseEntity.ok(nuevoCarrito);
    }

    // ✏️ Actualizar carrito existente
    @PutMapping("/{id}")
    public ResponseEntity<CarritoDTO> actualizarCarrito(@PathVariable Long id, @RequestBody carrito carritoActualizado) {
        CarritoDTO carritoDTO = carritoService.actualizarCarrito(id, carritoActualizado);
        return ResponseEntity.ok(carritoDTO);
    }

    // 🔎 Obtener carrito por ID
    @GetMapping("/{id}")
    public ResponseEntity<CarritoDTO> obtenerCarrito(@PathVariable Long id) {
        Optional<CarritoDTO> carrito = carritoService.obtenerCarritoPorId(id);
        return carrito.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // 📋 Listar todos los carritos
    @GetMapping
    public ResponseEntity<List<CarritoDTO>> listarCarritos() {
        return ResponseEntity.ok(carritoService.listarCarritos());
    }

    // ❌ Eliminar carrito por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrito(@PathVariable Long id) {
        carritoService.eliminarCarrito(id);
        return ResponseEntity.noContent().build();
    }

    // 🔎 Buscar carritos por cliente
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CarritoDTO>> buscarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(carritoService.buscarPorCliente(clienteId));
    }

    // 🔎 Buscar carrito por comprobante
    @GetMapping("/comprobante/{comprobanteId}")
    public ResponseEntity<CarritoDTO> buscarPorComprobante(@PathVariable Long comprobanteId) {
        CarritoDTO carrito = carritoService.buscarPorComprobante(comprobanteId);
        return carrito != null ? ResponseEntity.ok(carrito) : ResponseEntity.notFound().build();
    }

    // ➕ Agregar detalle al carrito
    @PostMapping("/{carritoId}/detalles")
    public ResponseEntity<CarritoDTO> agregarDetalle(@PathVariable Long carritoId, @RequestBody detalleCarrito detalle) {
        CarritoDTO carritoActualizado = carritoService.agregarDetalle(carritoId, detalle);
        return ResponseEntity.ok(carritoActualizado);
    }

    // 💳 Registrar un pago para un carrito (crear un nuevo pago asociado)
    @PostMapping("/{carritoId}/pago")
    public ResponseEntity<pago> registrarPago(@PathVariable Long carritoId, @RequestParam String metodoPago) {
        pago nuevoPago = carritoService.registrarPago(carritoId, metodoPago);
        return ResponseEntity.ok(nuevoPago);
    }

    // 🔄 Actualizar el estado del pago (por ejemplo, Pendiente -> Completado)
    @PutMapping("/pago/{pagoId}")
    public ResponseEntity<pago> actualizarEstadoPago(@PathVariable Long pagoId, @RequestParam String estado) {
        pago pagoActualizado = carritoService.actualizarEstadoPago(pagoId, estado);
        return ResponseEntity.ok(pagoActualizado);
    }

    // 🔎 Obtener el pago asociado a un carrito
    @GetMapping("/{carritoId}/pago")
    public ResponseEntity<pago> obtenerPagoPorCarrito(@PathVariable Long carritoId) {
        pago pago = carritoService.obtenerPagoPorCarrito(carritoId);
        return ResponseEntity.ok(pago);
    }
}
