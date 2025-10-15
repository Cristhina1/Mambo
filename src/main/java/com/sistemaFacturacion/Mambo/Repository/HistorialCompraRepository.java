package com.sistemaFacturacion.Mambo.Repository;

import com.sistemaFacturacion.Mambo.model.HistorialCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialCompraRepository extends JpaRepository<HistorialCompra, Long> {
    List<HistorialCompra> findByClienteId(Long clienteId);
}
