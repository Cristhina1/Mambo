package com.sistemaFacturacion.Mambo.mape.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioDTO {
    private Long id;
    private String tipoEnvio;
    private String direccionEnvio;
    private String ciudad;
    private Double costoEnvio;
    private String referencia;
}
