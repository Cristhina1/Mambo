package com.sistemaFacturacion.Mambo.Service;

import com.sistemaFacturacion.Mambo.entity.model.Producto;
import com.sistemaFacturacion.Mambo.entity.model.categoria;
import com.sistemaFacturacion.Mambo.mape.dto.ProductoDTO;
import com.sistemaFacturacion.Mambo.mape.mapeo.ProductoMape;
import com.sistemaFacturacion.Mambo.entity.Repository.CategoriaRepository;
import com.sistemaFacturacion.Mambo.entity.Repository.ProductoRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoMape  productoMape;
    private final GuardadoImgService guardadoImgservice;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository,
            ProductoMape productoMape,GuardadoImgService guardadoImgservic) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.productoMape = productoMape;
        this.guardadoImgservice = guardadoImgservic;
    }

    // Crear producto
    public ProductoDTO save(ProductoDTO dto) {
        Producto producto = productoMape.toEntity(dto);

        categoria categoria = categoriaRepository.findByNombre(dto.getCategoriaNombre())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        producto.setCategoria(categoria);

        if (dto.getImagenUrl() != null && !dto.getImagenUrl().isEmpty()) {
            String urlImagen = guardadoImgservice.guardarImagen(dto.getImagenUrl());
            producto.setImagenUrl(urlImagen);
        }
        Producto nuevo = productoRepository.save(producto);
        return productoMape.toDto(nuevo);
    }

    // Actualizar producto
    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());

        categoria categoria = categoriaRepository.findByNombre(dto.getCategoriaNombre())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        producto.setCategoria(categoria);

        // Si sube nueva imagen, reemplaza la anterior
        if (dto.getImagenUrl() != null && !dto.getImagenUrl().isEmpty()) {
            String urlImagen = guardadoImgservice.guardarImagen(dto.getImagenUrl());
            producto.setImagenUrl(urlImagen);
        }
        Producto nuevo = productoRepository.save(producto);
        return productoMape.toDto(nuevo);
    }


    public List<ProductoDTO> findAll() {
        return productoRepository.findAll()
        .stream()
        .map(productoMape::toDto)
        .toList();
    }


    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }
}
