package com.sistemaFacturacion.Mambo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private String nombre;
    private String categoria;   // ✅ agregado
    private Double precio;
    private Integer stock;
    private String descripcion; // ✅ agregado
    private Long Id;
}
