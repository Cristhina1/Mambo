package com.sistemaFacturacion.Mambo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemaFacturacion.Mambo.Service.ProductoService;
import com.sistemaFacturacion.Mambo.mape.dto.ProductoDTO;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {

    private final ProductoService productoService;

    public ProductoRestController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Listar todos
    // ✅ Listar todos los productos (entidades completas, útil si quieres imágenes y categorías)
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    // ✅ Crear nuevo producto desde API
    @PostMapping
    public ResponseEntity<ProductoDTO> create(@RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.save(productoDTO));
    }

    // ✅ Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.ok(productoService.actualizar(id, productoDTO));
    }

    // ✅ Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
