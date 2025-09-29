package com.sistemaFacturacion.Mambo.producto.controller;

import com.sistemaFacturacion.Mambo.model.ProductoImagen;
import com.sistemaFacturacion.Mambo.producto.service.ProductoImagenService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/imagenes")
public class ProductoImagenController {

    private final ProductoImagenService productoImagenService;

    // Inyección por constructor
    public ProductoImagenController(ProductoImagenService productoImagenService) {
        this.productoImagenService = productoImagenService;
    }

    // Crear imagen asociada a un producto
    @PostMapping("/{productoId}")
    public ResponseEntity<ProductoImagen> create(@PathVariable Long productoId,
                                                 @RequestBody ProductoImagen imagen) {
        return ResponseEntity.ok(productoImagenService.save(productoId, imagen));
    }

    // Listar todas
    @GetMapping
    public ResponseEntity<List<ProductoImagen>> getAll() {
        return ResponseEntity.ok(productoImagenService.findAll());
    }

    // Buscar por producto
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ProductoImagen>> getByProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(productoImagenService.findByProducto(productoId));
    }

}
