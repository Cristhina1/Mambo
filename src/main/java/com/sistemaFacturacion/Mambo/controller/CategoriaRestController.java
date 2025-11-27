package com.sistemaFacturacion.Mambo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sistemaFacturacion.Mambo.Service.CategoriaService;
import com.sistemaFacturacion.Mambo.mape.dto.CategoriaDTO;


@RestController
@RequestMapping("/api/categorias")
public class CategoriaRestController {
     private final CategoriaService categoriaService;

    public CategoriaRestController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listar() {
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> guardarCategoria(@RequestBody CategoriaDTO dto) {
        return ResponseEntity.ok(categoriaService.guardarCategoria(dto));
    }

    // Actualizar categoría
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> actualizarCategoria(
            @PathVariable Long id,
            @RequestBody CategoriaDTO dto) {

        dto.setId(id);  
        return ResponseEntity.ok(categoriaService.guardarCategoria(dto));
    }



   @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

    // 🔎 Buscar por nombre
    @GetMapping("/buscar")
    public ResponseEntity<CategoriaDTO> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(categoriaService.buscarPorNombre(nombre));
    }
}
