package com.sistemaFacturacion.Mambo.mape.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String categoriaNombre;
    private String icono;
    private Double precio;
    private Integer stock;
    private String descripcion; 
    private String img; //ruta q muestra
    private MultipartFile imagenUrl;       // ruta q guarda
    private String estadoStock;

}

