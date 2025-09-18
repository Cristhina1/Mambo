package com.sistemaFacturacion.Mambo.repository;

import com.sistemaFacturacion.Mambo.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNombreCompletoContainingIgnoreCaseOrNumeroDocumentoContainingIgnoreCase(String nombre, String numero);

    List<Cliente> findByTipoDocumento(String tipoDocumento);

    List<Cliente> findByEstado(String estado);
}
