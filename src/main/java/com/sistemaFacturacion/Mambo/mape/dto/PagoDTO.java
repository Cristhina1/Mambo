package com.sistemaFacturacion.Mambo.mape.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {
    private Long id;
    private Double precio;
    private String tipoPago;
    private LocalDateTime fechaPago;
}