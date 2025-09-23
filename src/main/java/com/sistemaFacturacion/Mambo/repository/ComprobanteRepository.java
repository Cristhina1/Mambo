package com.sistemaFacturacion.Mambo.repository;

import com.sistemaFacturacion.Mambo.model.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {

    // Buscar comprobantes por tipo (ejemplo: BOLETA, FACTURA)
    List<Comprobante> findByTipo(String tipo);

    // Buscar comprobantes por número de documento del cliente
    List<Comprobante> findByNumeroDocumento(String numeroDocumento);
}
