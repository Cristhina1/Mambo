package com.sistemaFacturacion.Mambo.mape.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraDTO {
    private Long id;
    private String numDocumento;
    private String tipoEnvio;
    private String nombreDestinario;
    private String contactoDestinatario;
    private String tipoPago;
    private Double total;
    private String estado;
    private String fechaCreacion;
    private String tipoComprobante;
    private List<DetalleCompraDto> detalles = new ArrayList<>();
}
