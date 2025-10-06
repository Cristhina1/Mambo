package com.sistemaFacturacion.Mambo.client.repository;

import com.sistemaFacturacion.Mambo.model.cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<cliente, Long> {

    // Listar todos los clientes activos
    // Buscar cliente por número de documento
    Optional<cliente> findByNumeroDocumento(String numeroDocumento);

    Optional<cliente> findByEmail(String email);
}
