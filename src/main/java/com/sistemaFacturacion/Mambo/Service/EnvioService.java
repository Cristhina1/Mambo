package com.sistemaFacturacion.Mambo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistemaFacturacion.Mambo.entity.Repository.EnvioRepository;
import com.sistemaFacturacion.Mambo.entity.model.Envio;
import com.sistemaFacturacion.Mambo.mape.dto.EnvioDTO;
import com.sistemaFacturacion.Mambo.mape.mapeo.EnvioMape;

@Service
public class EnvioService {
    @Autowired
    EnvioRepository envioRepository;
    @Autowired
    EnvioMape envioMape;

    
    public EnvioDTO guardar(EnvioDTO envioDTO) {
        Envio envio = envioMape.toEntity(envioDTO);
        if(envioDTO.getTipoEnvio().equals("DELIVERY")){
            envio.setPrecio(20.0);
        }else{
            envio.setPrecio(0.0);
        }

        Envio envioGuardado = envioRepository.save(envio);
        return envioMape.toDto(envioGuardado);
    }
}
