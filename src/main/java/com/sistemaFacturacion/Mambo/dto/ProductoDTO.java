package com.sistemaFacturacion.Mambo.dto;

import java.util.List;

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
    private Long categoriaID;   // ✅ agregado
    private Double precio;
    private Integer stock;
    private String descripcion; // ✅ agregado
    private List<MultipartFile> imagenes;
    private List<String> imagenesUrls;    // Para mostrar al consultar
}
