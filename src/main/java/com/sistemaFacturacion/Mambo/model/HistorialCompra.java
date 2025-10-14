package com.sistemaFacturacion.Mambo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "historial_compras")
public class HistorialCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId; // temporal si no hay login
    private String producto;
    private Integer cantidad;
    private Double precio;
    private Double total;
    private LocalDateTime fechaCompra = LocalDateTime.now();
}
