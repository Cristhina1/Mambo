package com.sistemaFacturacion.Mambo.mape.dto;

import com.sistemaFacturacion.Mambo.entity.model.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private TipoDocumento tipoDocumento;
    private String numDocumento;
    private String rol;
    private String numeroDocumento;
    private String nombreCompleto;
    private String email;
    private String telefono;
    private String contra;
}
