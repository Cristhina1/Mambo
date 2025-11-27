package com.sistemaFacturacion.Mambo.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sistemaFacturacion.Mambo.entity.Repository.DestinatarioRepository;
import com.sistemaFacturacion.Mambo.entity.model.Destinatario;
import com.sistemaFacturacion.Mambo.mape.dto.DestinatarioDTO;
import com.sistemaFacturacion.Mambo.mape.mapeo.DestinatarioMape;

public class DestinatarioService {
    @Autowired
    private DestinatarioRepository destinatarioRepository;

    @Autowired
    private DestinatarioMape destinatarioMape;
    
    public DestinatarioDTO crearDestinatario(DestinatarioDTO destinatarioDTO) {
        Destinatario destinatario = destinatarioMape.toEntity(destinatarioDTO);
        Destinatario guardado = destinatarioRepository.save(destinatario);
        return destinatarioMape.toDto(guardado);
    }

    public Destinatario obtenerDestinatarioPorId(Long id) {
        return destinatarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destinatario no encontrado"));
    }

    public DestinatarioDTO actualizar(Long id, DestinatarioDTO destinatarioDTO) {
        Destinatario existente = obtenerDestinatarioPorId(id);
        existente.setNombres(destinatarioDTO.getNombreCompleto());
        existente.setApellidos(destinatarioDTO.getApellidos());
        existente.setTelefono(destinatarioDTO.getTelefono());
        existente.setEmail(destinatarioDTO.getEmail());
        Destinatario actualizado = destinatarioRepository.save(existente);
        return destinatarioMape.toDto(actualizado);
    }
}
