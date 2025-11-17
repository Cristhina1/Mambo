package com.sistemaFacturacion.Mambo.Repository;

import com.sistemaFacturacion.Mambo.model.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {

    // Obtener todos los comprobantes de un cliente por su ID
    List<Comprobante> findByClienteId(Long clienteId);

    // Opcional: filtrar por fecha o estado
    List<Comprobante> findByClienteIdAndEstado(Long clienteId, String estado);
}
