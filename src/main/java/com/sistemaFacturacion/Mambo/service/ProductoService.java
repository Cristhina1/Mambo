package com.sistemaFacturacion.Mambo.service;

import com.sistemaFacturacion.Mambo.model.Producto;
import com.sistemaFacturacion.Mambo.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    // 🔹 Inyección por constructor (más profesional)
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Crear o actualizar producto
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    // Listar todos
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    // Buscar por ID
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    // Eliminar por ID
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    // Buscar por nombre
    public List<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Buscar por categoría
    public List<Producto> findByCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }
}
