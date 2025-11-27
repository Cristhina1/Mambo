package com.sistemaFacturacion.Mambo.mape.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    
    private Long id;
    private String nombre;
    private String icono;
    private MultipartFile iconoUrl;
}
