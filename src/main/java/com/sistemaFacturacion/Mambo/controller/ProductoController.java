package com.sistemaFacturacion.Mambo.controller;

import com.sistemaFacturacion.Mambo.model.Producto;
import com.sistemaFacturacion.Mambo.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    // 🔹 Inyección por constructor
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Crear producto
    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.save(producto));
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Long id) {
        return productoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.findById(id)
                .map(existing -> {
                    producto.setId(id); // aseguramos que actualice el existente
                    return ResponseEntity.ok(productoService.save(producto));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (productoService.findById(id).isPresent()) {
            productoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Buscar por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> searchByNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productoService.findByNombre(nombre));
    }

    // Buscar por categoría
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Producto>> getByCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(productoService.findByCategoria(categoriaId));
    }
}
