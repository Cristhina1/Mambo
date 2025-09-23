package com.sistemaFacturacion.Mambo.controller;

import com.sistemaFacturacion.Mambo.model.categoria;
import com.sistemaFacturacion.Mambo.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // ➕ Crear categoría
    @PostMapping
    public categoria crearCategoria(@RequestBody categoria categoria) {
        return categoriaService.guardarCategoria(categoria);
    }

    // ✏️ Actualizar categoría
    @PutMapping("/{id}")
    public ResponseEntity<categoria> actualizarCategoria(@PathVariable Long id, @RequestBody categoria categoria) {
        return categoriaService.obtenerCategoriaPorId(id)
                .map(cat -> {
                    cat.setNombre(categoria.getNombre());
                    return ResponseEntity.ok(categoriaService.guardarCategoria(cat));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔎 Obtener categoría por id
    @GetMapping("/{id}")
    public ResponseEntity<categoria> obtenerCategoria(@PathVariable Long id) {
        return categoriaService.obtenerCategoriaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 📋 Listar todas
    @GetMapping
    public List<categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    // ❌ Eliminar categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    // 🔎 Buscar por nombre
    @GetMapping("/buscar")
    public ResponseEntity<categoria> buscarPorNombre(@RequestParam String nombre) {
        return categoriaService.buscarPorNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
