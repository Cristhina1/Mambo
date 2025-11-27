package com.sistemaFacturacion.Mambo.entity.Repository;

import com.sistemaFacturacion.Mambo.entity.model.pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<pago, Long> {
}
