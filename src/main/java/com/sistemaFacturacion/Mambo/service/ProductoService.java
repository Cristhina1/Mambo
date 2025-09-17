package com.sistemaFacturacion.Mambo.service;

import com.sistemaFacturacion.Mambo.dto.ProductoDTO;
import com.sistemaFacturacion.Mambo.model.Producto;
import com.sistemaFacturacion.Mambo.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoDTO> listarProductos() {
        return productoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public void guardarProducto(ProductoDTO dto) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setCategoria(dto.getCategoria());     // ✅ agregado
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        p.setDescripcion(dto.getDescripcion()); // ✅ agregado
        productoRepository.save(p);
    }

    private ProductoDTO convertirADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setCategoria(producto.getCategoria());     // ✅ agregado
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setDescripcion(producto.getDescripcion()); // ✅ agregado
        return dto;
    }
    public void eliminarProducto(Long id) {
    productoRepository.deleteById(id);
}

}
