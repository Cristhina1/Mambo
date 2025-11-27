package com.sistemaFacturacion.Mambo.entity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaFacturacion.Mambo.entity.model.DetalleCompra;

public interface DetalleCompraRepository extends JpaRepository<DetalleCompra, Long> {
    
}
