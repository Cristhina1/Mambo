package com.sistemaFacturacion.Mambo.producto.repository;

import com.sistemaFacturacion.Mambo.model.ProductoImagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoImagenRepository extends JpaRepository<ProductoImagen, Long> {
    // Buscar todas las imágenes de un producto
    List<ProductoImagen> findByProductoId(Long productoId);
}
