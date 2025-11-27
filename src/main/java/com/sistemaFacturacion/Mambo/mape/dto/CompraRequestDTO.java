package com.sistemaFacturacion.Mambo.mape.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraRequestDTO {
    private EnvioDTO envio;
    private DestinatarioDTO destinatario;
    private PagoDTO pago;

    private String tipoComprobante;
    private List<DetalleCompraDto> detalles;
}
