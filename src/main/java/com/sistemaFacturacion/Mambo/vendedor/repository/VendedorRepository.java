package com.sistemaFacturacion.Mambo.vendedor.repository;

import com.sistemaFacturacion.Mambo.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    boolean existsByDni(String dni);
}
