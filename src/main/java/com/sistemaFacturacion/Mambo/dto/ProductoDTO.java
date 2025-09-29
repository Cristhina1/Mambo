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
    private Long categoriaID;   
    private Double precio;
    private Integer stock;
    private String descripcion; 
    private List<MultipartFile> imagenes;
    private List<String> imagenesUrls;    // Para mostrar al consultar
     private String estadoStock;

    public void calcularEstadoStock() {
        if (stock == null || stock == 0) {
            estadoStock = "Sin stock";
        } else if (stock <= 5) {
            estadoStock = "Stock bajo";
        } else {
            estadoStock = "En stock";
        }
    }
}
