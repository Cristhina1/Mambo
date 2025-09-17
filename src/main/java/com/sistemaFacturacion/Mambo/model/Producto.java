package com.sistemaFacturacion.Mambo.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String categoria;   // ✅ agregado
    private Double precio;
    private Integer stock;
    private String descripcion; // ✅ agregado

}
