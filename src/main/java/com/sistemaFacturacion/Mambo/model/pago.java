package com.sistemaFacturacion.Mambo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Table(name = "pagos")
@Data
public class pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double precio;   // Total del carrito o monto pagado
    private String metodo;   // Ejemplo: "Efectivo", "Yape", "Tarjeta"
    private String estado;   // Ejemplo: "PENDIENTE", "COMPLETADO", "CANCELADO"
    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago = LocalDateTime.now();

    // Relación bidireccional con carrito
    @OneToOne(mappedBy = "pago")
    private carrito carrito;
}
