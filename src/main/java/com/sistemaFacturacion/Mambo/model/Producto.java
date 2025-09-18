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

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String categoria;   // ✅ agregado

    @Column(nullable = false, precision = 10, scale = 2)
    private Double precio;

    @Column(nullable = false, precision = 10, scale = 0)
    private Integer stock;

    @Column(length = 255)
    private String descripcion; // ✅ agregado

}
