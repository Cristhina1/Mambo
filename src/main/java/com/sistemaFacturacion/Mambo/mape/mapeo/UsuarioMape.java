package com.sistemaFacturacion.Mambo.mape.mapeo;

import com.sistemaFacturacion.Mambo.entity.model.Usuario;
import com.sistemaFacturacion.Mambo.mape.dto.VendedorDTO;

public class UsuarioMape {
    public VendedorDTO toDto(Usuario user){
        VendedorDTO dto = new VendedorDTO();
        dto.setId(user.getId());
        dto.setTipoDocumento(user.getTipoDocumento().name());
        dto.setRol(dto.getRol());
        dto.setNombreCompleto(dto.getNombreCompleto());
        dto.setNumeroDocumento(dto.getNumeroDocumento());
        dto.setEmail(dto.getEmail());
        dto.setTelefono(dto.getTelefono());
        return dto;
    }
}
