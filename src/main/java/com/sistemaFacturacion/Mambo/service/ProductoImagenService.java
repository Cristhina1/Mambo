package com.sistemaFacturacion.Mambo.service;

import com.sistemaFacturacion.Mambo.model.Producto;
import com.sistemaFacturacion.Mambo.model.ProductoImagen;
import com.sistemaFacturacion.Mambo.repository.ProductoImagenRepository;
import com.sistemaFacturacion.Mambo.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoImagenService {

    private final ProductoImagenRepository productoImagenRepository;
    private final ProductoRepository productoRepository;

    // Inyección por constructor
    public ProductoImagenService(ProductoImagenRepository productoImagenRepository,
                                 ProductoRepository productoRepository) {
        this.productoImagenRepository = productoImagenRepository;
        this.productoRepository = productoRepository;
    }

    // Crear imagen asociada a un producto
    public ProductoImagen save(Long productoId, ProductoImagen imagen) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + productoId));
        imagen.setProducto(producto);
        return productoImagenRepository.save(imagen);
    }

    // Listar todas
    public List<ProductoImagen> findAll() {
        return productoImagenRepository.findAll();
    }

    // Listar por producto
    public List<ProductoImagen> findByProducto(Long productoId) {
        return productoImagenRepository.findByProductoId(productoId);
    }

    // Buscar por ID
    public Optional<ProductoImagen> findById(Long id) {
        return productoImagenRepository.findById(id);
    }

    // Eliminar por ID
    public void deleteById(Long id) {
        productoImagenRepository.deleteById(id);
    }
}
