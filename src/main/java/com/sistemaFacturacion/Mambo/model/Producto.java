package com.sistemaFacturacion.Mambo.model;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private categoria categoria;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double precio;

    @Column(nullable = false, precision = 10, scale = 0)
    private Integer stock;

    @Column(columnDefinition = "TEXT")
    private String descripcion; // ✅ agregado
    
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoImagen> imagenes = new ArrayList<>();

}
