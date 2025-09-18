package com.sistemaFacturacion.Mambo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numeroFactura;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private Double total;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private cliente cliente;
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

}