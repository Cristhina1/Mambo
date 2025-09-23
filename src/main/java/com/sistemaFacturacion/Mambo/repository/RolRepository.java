package com.sistemaFacturacion.Mambo.repository;

import com.sistemaFacturacion.Mambo.model.rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<rol, Long> {

    // Buscar rol por nombre (ejemplo: "ADMIN", "USER")
    Optional<rol> findByNombre(String nombre);
}
