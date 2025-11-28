package com.sistemaFacturacion.Mambo.mape.mapeo;

import org.springframework.stereotype.Component;

import com.sistemaFacturacion.Mambo.entity.model.Destinatario;
import com.sistemaFacturacion.Mambo.mape.dto.DestinatarioDTO;

@Component
public class DestinatarioMape {
    public Destinatario toEntity(DestinatarioDTO dto){
        Destinatario destinatario = new Destinatario();
        destinatario.setNombres(dto.getNombreCompleto());
        destinatario.setApellidos(dto.getApellidos());
        destinatario.setTelefono(dto.getTelefono());
        destinatario.setEmail(dto.getEmail());
        return destinatario;
    }

    public DestinatarioDTO toDto(Destinatario destinatario) {
        DestinatarioDTO dto = new DestinatarioDTO();
        dto.setId(destinatario.getId());
        dto.setNombreCompleto(destinatario.getNombres());
        dto.setApellidos(destinatario.getApellidos());
        dto.setTelefono(destinatario.getTelefono());
        dto.setEmail(destinatario.getEmail());
        return dto;
    }
}
