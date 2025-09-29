package com.sistemaFacturacion.Mambo.client.repository;

import com.sistemaFacturacion.Mambo.model.cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<cliente, Long> {

    // Buscar cliente por número de documento
    Optional<cliente> findByNumeroDocumento(String numeroDocumento);

    // Buscar cliente por email
    Optional<cliente> findByEmail(String email);

}
