package com.sistemaFacturacion.Mambo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class sesionCliente {
    private String contra;
    private String numeroDocumento;
}
