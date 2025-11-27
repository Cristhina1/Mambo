package com.sistemaFacturacion.Mambo.Service;

import org.springframework.stereotype.Service;

import com.sistemaFacturacion.Mambo.entity.Repository.CategoriaRepository;
import com.sistemaFacturacion.Mambo.entity.model.categoria;
import com.sistemaFacturacion.Mambo.mape.dto.CategoriaDTO;
import com.sistemaFacturacion.Mambo.mape.mapeo.CategoriaMape;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMape categoriaMape;
    private final GuardadoImgService guardadoImgService;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMape categoriaMape,GuardadoImgService guardadoImgService) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMape = categoriaMape;
        this.guardadoImgService = guardadoImgService;
    }

    // ➕ Crear o actualizar categoría
    public CategoriaDTO guardarCategoria(CategoriaDTO dto) {
        categoria categoria = categoriaMape.toEntity(dto);
        if (dto.getIconoUrl() != null && !dto.getIconoUrl().isEmpty()) {
            String urlImagen = guardadoImgService.guardarImagen(dto.getIconoUrl());
            categoria.setImgCategoira(urlImagen);
        }
        categoria save = categoriaRepository.save(categoria);
        return categoriaMape.toDto(save);
    }

    // 🔎 Buscar categoría por id
    public CategoriaDTO obtenerCategoriaPorId(Long id) {
        categoria entity = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));

        return categoriaMape.toDto(entity);
    }

    // 📋 Listar todas las categorías
    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMape::toDto)
                .collect(Collectors.toList());
    }

    // ❌ Eliminar categoría
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    // 🔎 Buscar categoría por nombre
    public CategoriaDTO buscarPorNombre(String nombre) {
        categoria entity = categoriaRepository.findByNombre(nombre)
                .orElseThrow(() -> new RuntimeException("No existe esa categoría"));

        return categoriaMape.toDto(entity);
    }

}
