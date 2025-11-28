package com.sistemaFacturacion.Mambo.mape.mapeo;

import org.springframework.stereotype.Component;

import com.sistemaFacturacion.Mambo.entity.model.Producto;
import com.sistemaFacturacion.Mambo.mape.dto.ProductoDTO;

@Component
public class ProductoMape {
    public ProductoDTO toDto(Producto producto){
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setCategoriaNombre(producto.getCategoria().getNombre());
        dto.setNombre(producto.getNombre());
        dto.setImg(producto.getCategoria().getImgCategoira());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setEstadoStock(producto.getStock() == null || producto.getStock() == 0 ? "Sin stock"
                : (producto.getStock() <= 5 ? "Stock bajo" : "En stock"));
        dto.setImg(producto.getImagenUrl());
        return dto;
    }

    public Producto toEntity(ProductoDTO dto){
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        return producto;
    }
}
