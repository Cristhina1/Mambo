package com.sistemaFacturacion.Mambo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialComprobanteDTO {
    private Long id;                // ID del comprobante
    private String clienteNombre;   // Nombre del cliente
    private String tipoDocumento;   // Tipo de documento al momento de emisión
    private String numeroDocumento; // Número de documento al momento de emisión
    private String tipoComprobante; // BOLETA o FACTURA
    private LocalDateTime fecha;    // Fecha de creación
    private Double total;           // Total pagado
}

