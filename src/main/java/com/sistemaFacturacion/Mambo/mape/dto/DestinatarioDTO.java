package com.sistemaFacturacion.Mambo.mape.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinatarioDTO {
    private Long id;
    private String nombreCompleto;
    private String telefono;
    private String email;
    private String apellidos;
    private String numDocumento;
}
