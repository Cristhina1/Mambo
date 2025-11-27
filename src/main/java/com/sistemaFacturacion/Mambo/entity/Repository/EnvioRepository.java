package com.sistemaFacturacion.Mambo.entity.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemaFacturacion.Mambo.entity.model.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Long> {
    
}
