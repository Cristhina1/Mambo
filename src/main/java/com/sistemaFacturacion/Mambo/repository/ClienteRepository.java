package com.sistemaFacturacion.Mambo.repository;

import com.sistemaFacturacion.Mambo.model.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<cliente, Long> {

    List<cliente> findByNombreCompletoContainingIgnoreCaseOrNumeroDocumentoContainingIgnoreCase(String nombre, String numero);

    List<cliente> findByTipoDocumento(String tipoDocumento);

    List<cliente> findByEstado(String estado);
}
