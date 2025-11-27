package com.sistemaFacturacion.Mambo.entity.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private cliente cliente;

    @Enumerated(EnumType.STRING)
    TipoComprobante tipoComprobante;

    @ManyToOne
    @JoinColumn(name = "envio_id")
    private Envio envio;

    // Destinatario elegido
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destinatario_id")
    private Destinatario destinatario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pago_id", nullable = false)
    private pago pago;

    @Enumerated(EnumType.STRING)
    private TipoEstado estadoPago;

    // Total del carrito incluyendo delivery
    private Double total;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCompra> detalles = new ArrayList<>();
}
