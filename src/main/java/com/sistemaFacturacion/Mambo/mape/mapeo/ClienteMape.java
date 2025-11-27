package com.sistemaFacturacion.Mambo.mape.mapeo;

import com.sistemaFacturacion.Mambo.entity.model.cliente;
import com.sistemaFacturacion.Mambo.mape.dto.ClienteDTO;

public class ClienteMape {
    public ClienteDTO toDto(cliente cliente){
        ClienteDTO dto = new ClienteDTO();
        dto.setTipoDocumento(cliente.getTipoDocumento());
        dto.setNumDocumento(cliente.getNumeroDocumento());
        dto.setRol(cliente.getRol().getNombre());
        dto.setNumeroDocumento(cliente.getNumeroDocumento());
        dto.setNombreCompleto(cliente.getNombreCompleto());
        dto.setEmail(cliente.getEmail());
        dto.setTelefono(cliente.getTelefono());
        dto.setContra(cliente.getContra());
        return dto;
    }

    public cliente toEntity(ClienteDTO dto){
        cliente cliente = new cliente();
        cliente.setId(dto.getId());
        cliente.setTipoDocumento(dto.getTipoDocumento());
        cliente.setNumeroDocumento(dto.getNumeroDocumento());
        cliente.setNombreCompleto(dto.getNombreCompleto());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        return cliente;
    }
}
