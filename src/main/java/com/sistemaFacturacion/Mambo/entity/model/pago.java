package com.sistemaFacturacion.Mambo.entity.model;

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

    private Double precio; 
     
    @Column(name = "tipo_pago")
    @Enumerated(EnumType.STRING)
    private TipoPago tipoPago; 

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago = LocalDateTime.now();

    @OneToOne(mappedBy = "pago")
    private Compra carrito;

}
