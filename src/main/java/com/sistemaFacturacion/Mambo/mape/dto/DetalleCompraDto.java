package com.sistemaFacturacion.Mambo.mape.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCompraDto {

    private Long id;
    private Long productoId;
    private Long compraId;
    private String nombreProducto;
    private Double precioUnitario;
    private int cantidad;
    private Double subtotal;
}
