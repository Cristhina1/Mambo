package com.sistemaFacturacion.Mambo.mape.dto;

import lombok.Data;

@Data
public class VendedorDTO {
    private Long id;
    private String nombreCompleto;
    private String rol;
    private String tipoDocumento;
    private String numeroDocumento;
    private String email;
    private String telefono;
    private String contra;
}