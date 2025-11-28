package com.sistemaFacturacion.Mambo.mape.mapeo;

import org.springframework.stereotype.Component;

import com.sistemaFacturacion.Mambo.entity.model.Envio;
import com.sistemaFacturacion.Mambo.entity.model.TipoEnvio;
import com.sistemaFacturacion.Mambo.mape.dto.EnvioDTO;

@Component
public class EnvioMape {

    public Envio toEntity(EnvioDTO dto) {
        Envio envio = new Envio();
        envio.setTipoEnvio(TipoEnvio.valueOf(dto.getTipoEnvio()));
        envio.setDireccion(dto.getDireccionEnvio());
        envio.setCiudad(dto.getCiudad());
        envio.setReferencia(dto.getReferencia());
        envio.setPrecio(dto.getCostoEnvio());
        return envio;
    }

    public EnvioDTO toDto(Envio envio) {
        EnvioDTO dto = new EnvioDTO();
        dto.setId(envio.getId());
        dto.setTipoEnvio(envio.getTipoEnvio().name());
        dto.setDireccionEnvio(envio.getDireccion());
        dto.setCiudad(envio.getCiudad());
        dto.setReferencia(envio.getReferencia());
        return dto;
    }
}
